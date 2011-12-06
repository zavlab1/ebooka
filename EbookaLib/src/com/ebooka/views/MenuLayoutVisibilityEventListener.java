/**
 * 
 */
package com.ebooka.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ebooka.R;
import com.ebooka.events.BringUpZoomControlsListener;

/**
 * @author iivanenko
 * 
 */
public class MenuLayoutVisibilityEventListener implements BringUpZoomControlsListener {
    View menuLayout;
    View nextPage;
    View prevPage;
    View pages;
    ImageView imageMenuArrow;

    public MenuLayoutVisibilityEventListener(Context context) {
    	menuLayout =((Activity)context).findViewById(R.id.menuLayout);
    	nextPage =((Activity)context).findViewById(R.id.nextPage);
    	prevPage =((Activity)context).findViewById(R.id.prevPage);
        pages = ((Activity) context).findViewById(R.id.pagsLayout);
    	imageMenuArrow = (ImageView)((Activity)context).findViewById(R.id.imageMenuArrow);

    }

    public void toggleZoomControls() {
        if (menuLayout.getVisibility() == View.VISIBLE) {
        	//invisible
            menuLayout.setVisibility(View.GONE);
            nextPage.setVisibility(View.GONE);
            prevPage.setVisibility(View.GONE);
            pages.setVisibility(View.GONE);
            imageMenuArrow.setImageResource(android.R.drawable.arrow_down_float);
            
        } else {
            menuLayout.setVisibility(View.VISIBLE);
            nextPage.setVisibility(View.VISIBLE);
            prevPage.setVisibility(View.VISIBLE);
            pages.setVisibility(View.VISIBLE);
            imageMenuArrow.setImageResource(android.R.drawable.arrow_up_float);
        }
    }

}
