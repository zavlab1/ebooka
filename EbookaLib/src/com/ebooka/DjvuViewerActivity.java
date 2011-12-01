package com.ebooka;

import org.vudroid.djvudroid.codec.DjvuContext;

public class DjvuViewerActivity extends BaseViewerActivity
{
    @Override
    protected DecodeService createDecodeService()
    {
        return new DecodeServiceBase(new DjvuContext());
    }
}
