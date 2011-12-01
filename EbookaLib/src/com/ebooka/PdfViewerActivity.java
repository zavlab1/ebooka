package com.ebooka;

import org.vudroid.pdfdroid.codec.PdfContext;

public class PdfViewerActivity extends BaseViewerActivity
{
    @Override
    protected DecodeService createDecodeService()
    {
        return new DecodeServiceBase(new PdfContext());
    }
}
