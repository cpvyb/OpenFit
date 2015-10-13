package com.samsung.android.sdk.cup;

import android.graphics.Bitmap;

public class ScupSpiCodec {
    Bitmap bitmap;

    public static native byte[] encode(Bitmap bitmap, int i);

    public static native byte[] encodeExt(Bitmap bitmap, int i, int i2, int i3, int i4);

    static {
        System.loadLibrary("SpiCodec");
    }

    public static byte[] encode(Bitmap bitmap) {
        return encode(bitmap, 28);
    }

    public static byte[] encodeWithoutAlpha(Bitmap bitmap) {
        return encodeExt(bitmap, 28, 1, 0, 0);
    }

    public static byte[] encodeWithoutTransparent(Bitmap bitmap, int complexity) {
        return encodeExt(bitmap, 28, 0, 1, complexity);
    }

    public static byte[] encodeWithoutTransparentBg(Bitmap bitmap, int complexity) {
        return encodeExt(bitmap, 12, 0, 1, complexity);
    }
}
