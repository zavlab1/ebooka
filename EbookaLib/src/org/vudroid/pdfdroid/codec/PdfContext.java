package org.vudroid.pdfdroid.codec;

import android.content.ContentResolver;

import com.ebooka.VuDroidLibraryLoader;
import com.ebooka.codec.CodecContext;
import com.ebooka.codec.CodecDocument;

public class PdfContext implements CodecContext
{
    static
    {
        VuDroidLibraryLoader.load();
    }

    public CodecDocument openDocument(String fileName)
    {
        return PdfDocument.openDocument(fileName, "");
    }

    public void setContentResolver(ContentResolver contentResolver)
    {
        //TODO
    }

    public void recycle() {
    }
}
