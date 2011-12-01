/**
 * 
 */
package com.ebooka.views;

import com.ebooka.events.BringUpZoomControlsListener;

import android.view.View;

/**
 * @author iivanenko
 * 
 */
public class PageViewToolsLayout implements BringUpZoomControlsListener {
    View view;

    public PageViewToolsLayout(View view) {
        this.view = view;
    }

    public void toggleZoomControls() {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

}
