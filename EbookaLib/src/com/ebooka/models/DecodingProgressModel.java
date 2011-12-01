package com.ebooka.models;

import com.ebooka.events.DecodingProgressListener;
import com.ebooka.events.EventDispatcher;

public class DecodingProgressModel extends EventDispatcher
{
    private int currentlyDecoding;

    public void increase()
    {
        currentlyDecoding++;
        dispatchChanged();
    }

    private void dispatchChanged()
    {
        dispatch(new DecodingProgressListener.DecodingProgressEvent(currentlyDecoding));
    }

    public void decrease()
    {
        currentlyDecoding--;
        dispatchChanged();
    }
}
