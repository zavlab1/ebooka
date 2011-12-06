package com.ebooka.presentation;

import java.io.File;
import java.util.Collections;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebooka.R;

public class SearchBrowserAdapter extends BaseAdapter
{
    private List<File> uris = Collections.emptyList();

    public int getCount()
    {
        return uris.size();
    }

    public File getItem(int i)
    {
        return uris.get(i);
    }

    public long getItemId(int i)
    {
        return i; 
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        final View browserItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browseritem, viewGroup, false);
        final ImageView imageView = (ImageView) browserItem.findViewById(R.id.browserItemIcon);
        final File file = uris.get(i);
        final TextView textView = (TextView) browserItem.findViewById(R.id.browserItemText);
        textView.setText(file.getName());
		Log.d("DEBUG", file.getName());
        imageView.setImageResource(R.drawable.book);
        return browserItem;
    }

    public void setUris(List<File> uris)
    {
        this.uris = uris;
		notifyDataSetInvalidated();
    }
}
