/**
 * 
 */
package com.ebooka.views;

import com.ebooka.R;
import com.ebooka.events.BringUpZoomControlsListener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * @author iivanenko
 * 
 */
public class MenuLayoutVisibilityEventListener implements BringUpZoomControlsListener {
    View menuLayout;
    View nextPage;
    View prevPage;
    ImageView imageMenuArrow;

    public MenuLayoutVisibilityEventListener(Context context) {
    	menuLayout =((Activity)context).findViewById(R.id.menuLayout);
    	nextPage =((Activity)context).findViewById(R.id.nextPage);
    	prevPage =((Activity)context).findViewById(R.id.prevPage);
    	imageMenuArrow = (ImageView)((Activity)context).findViewById(R.id.imageMenuArrow);
    }

    public void toggleZoomControls() {
        if (menuLayout.getVisibility() == View.VISIBLE) {
        	//invisible
            menuLayout.setVisibility(View.GONE);
            nextPage.setVisibility(View.GONE);
            prevPage.setVisibility(View.GONE);
            imageMenuArrow.setImageResource(android.R.drawable.arrow_down_float);
            
        } else {
            menuLayout.setVisibility(View.VISIBLE);
            nextPage.setVisibility(View.VISIBLE);
            prevPage.setVisibility(View.VISIBLE);
            imageMenuArrow.setImageResource(android.R.drawable.arrow_up_float);
        }
    }

}
