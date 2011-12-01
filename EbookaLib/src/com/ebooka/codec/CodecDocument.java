package com.ebooka.codec;

public interface CodecDocument {
    CodecPage getPage(int pageNumber);

    int getPageCount();

    void recycle();
}
