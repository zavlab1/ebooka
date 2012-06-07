package com.ebooka;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TextImageListActivity extends ListActivity {
	final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    
    /**
     * The SimpleAdapter expects an integer or string that specifies a resource or image URI:
     * public void setViewImage (ImageView v, String value)
     * Called by bindView() to set the image for an ImageView but only if there is no existing ViewBinder
     * or if the existing ViewBinder cannot handle binding to an ImageView. 
     * By default, the value will be treated as an image resource. 
     * If the value cannot be used as an image resource, the value is used as an image Uri. 
     * This method is called instead of setViewImage(ImageView, int) if the supplied data is not an int or Integer.
     * That is why we create our ViewBinder object and than set it for adapter by setViewBinder method
     */
    private final SimpleAdapter.ViewBinder mViewBinder =
		    new SimpleAdapter.ViewBinder() {
		        @Override
		        public boolean setViewValue(
		                final View view,
		                final Object data,
		                final String textRepresentation) {

		            if (view instanceof ImageView) {
		                ((ImageView) view).setImageBitmap((Bitmap) data);
		                return true;
		            }
		            return false;
		        }
		    };
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Map<String, String> rawRowsContent = new HashMap<String, String>();
        
        /* it is needed to realize method 
         * Map<String, String> rawRowsContent = requestForContent();
         * for getting of data 
         */
        
        /* for example */
        rawRowsContent.put("Евро-2012. Цифры и факты", "http://static.ozone.ru/multimedia/books_covers/c200/1005373625.jpg");
        rawRowsContent.put("UEFA Euro 2012", "http://static.ozone.ru/multimedia/books_covers/c200/1005289501.jpg");
        
        ListView lv = getListView();
               
        List<HashMap<String, Object>> rowsContent = getRowsContent(rawRowsContent);
        
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };
        
        SimpleAdapter adapter = new SimpleAdapter(this, rowsContent, R.layout.text_image_row,
        		from, new int[] {R.id.author_and_title,R.id.cover });
        
        adapter.setViewBinder(mViewBinder);
        lv.setAdapter(adapter);
    }
	
	private List<HashMap<String, Object>> getRowsContent(Map<String, String> rawRowsContent) {
		
		ArrayList<HashMap<String, Object>> readyRowsContent = new ArrayList<HashMap<String, Object>>();
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
		
        for (String text : rawRowsContent.keySet()) {
			String imageURL = rawRowsContent.get(text);
			HashMap<String, Object> rowMap = new HashMap<String, Object>();
			Bitmap bm = LoadImage(imageURL, bmOptions);
	        rowMap.put(ATTRIBUTE_NAME_TEXT, text);
			rowMap.put(ATTRIBUTE_NAME_IMAGE, bm);
	        readyRowsContent.add(rowMap);
		}
		return readyRowsContent;
	}
	
	private Bitmap LoadImage(String URL, BitmapFactory.Options options) {
		Bitmap bitmap = null;
		InputStream in = null;       
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) { Log.e("tag1", e1.toString()); }
        
        return bitmap;               
	}

	private InputStream OpenHttpConnection(String strURL) throws IOException{
		InputStream inputStream = null;
		URL url = new URL(strURL);
		URLConnection conn = url.openConnection();
		try {
			HttpURLConnection httpConn = (HttpURLConnection)conn;
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpConn.getInputStream();
			}
		} catch (Exception ex) { Log.e("tag2", ex.toString()); }
		
		return inputStream;
	}
}
