package com.ebooka;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ebooka.io.FileDirUtils;
import com.ebooka.presentation.BrowserAdapter;
import com.ebooka.presentation.SearchBrowserAdapter;
import com.ebooka.presentation.UriBrowserAdapter;

public abstract class BaseBrowserActivity extends Activity {
    private BrowserAdapter adapter;
    private static final String CURRENT_DIRECTORY = "currentDirectory";
    private UriBrowserAdapter recentAdapter;
    private SearchBrowserAdapter searchAdapter;
    private ViewerPreferences viewerPreferences;
    protected final FileFilter filter;
    private ListView listView;

    public BaseBrowserActivity() {
        this.filter = createFileFilter();
    }

    protected abstract FileFilter createFileFilter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        viewerPreferences = new ViewerPreferences(this);

        View tab1 = (View) findViewById(R.id.tab1);
        tab1.setOnClickListener(onTab1);
        tab1.setClickable(true);

        View tab2 = (View) findViewById(R.id.tab2);
        tab2.setOnClickListener(onTab2);

        View tab3 = (View) findViewById(R.id.tab3);
        tab3.setOnClickListener(onTab3);

        tabs = Arrays.asList(tab1, tab2, tab3);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new BrowserAdapter(this, filter);
        recentAdapter = new UriBrowserAdapter();
        searchAdapter = new SearchBrowserAdapter();

        activateTab1(tab1);
    }

    public void unselectTabs() {
        for (View tab : tabs) {
            tab.setSelected(false);
        }
    }

    View.OnClickListener onTab1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activateTab1(v);

        }
    };

    private void activateTab1(View v) {
        unselectTabs();
        v.setSelected(true);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @SuppressWarnings({ "unchecked" })
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final File file = ((AdapterView<BrowserAdapter>) adapterView).getAdapter().getItem(i);
            if (file.isDirectory()) {
                setCurrentDir(file);
            } else {
                showDocument(file);
            }
        }
    };

    View.OnClickListener onTab2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setTitle(R.string.app_name);
            unselectTabs();
            v.setSelected(true);
            listView.setAdapter(recentAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressWarnings({ "unchecked" })
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showDocument(((AdapterView<UriBrowserAdapter>) adapterView).getAdapter().getItem(i));
                }
            });
        }
    };

    View.OnClickListener onTab3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setTitle(R.string.app_name);
            unselectTabs();
            v.setSelected(true);
            listView.setAdapter(searchAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressWarnings({ "unchecked" })
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    File uri = ((AdapterView<SearchBrowserAdapter>) adapterView).getAdapter().getItem(i);
                    showDocument(uri);
                }
            });

            new AsyncTask<Void, Void, Void>() {

                private List<File> recurcive = new ArrayList<File>();
                ProgressDialog dialog;

                @Override
                protected Void doInBackground(Void... params) {
                    recurcive.addAll(FileDirUtils.search(Environment.getExternalStorageDirectory(), ".pdf"));
                    recurcive.addAll(FileDirUtils.search(new File("/media"), ".pdf"));
                    recurcive.addAll(FileDirUtils.search(new File("/emmc"), ".pdf"));
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    dialog = ProgressDialog.show(BaseBrowserActivity.this, "", getString(R.string.searching_please_wait_), true);
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(Void result) {
                    searchAdapter.setUris(recurcive);
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {

                    }
                };
            }.execute();

        }

    };

    private List<View> tabs;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final File sdcardPath = new File("/sdcard");
        if (sdcardPath.exists()) {
            setCurrentDir(sdcardPath);
        } else {
            setCurrentDir(new File("/"));
        }
        if (savedInstanceState != null) {
            final String absolutePath = savedInstanceState.getString(CURRENT_DIRECTORY);
            if (absolutePath != null) {
                setCurrentDir(new File(absolutePath));
            }
        }
    }

    private void showDocument(File file) {
        showDocument(Uri.fromFile(file));
    }

    protected abstract void showDocument(Uri uri);

    private void setCurrentDir(File newDir) {
        adapter.setCurrentDirectory(newDir);
        setTitle(newDir.getAbsolutePath());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_DIRECTORY, adapter.getCurrentDirectory().getAbsolutePath());
    }

    @Override
    protected void onResume() {
        super.onResume();
        recentAdapter.setUris(viewerPreferences.getRecent());
    }
}
