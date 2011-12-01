package com.ebooka.codec;

import android.content.ContentResolver;

public interface CodecContext
{
    CodecDocument openDocument(String fileName);

    void setContentResolver(ContentResolver contentResolver);

    void recycle();
}
