package com.beyondphysics.network.cache;

import android.graphics.Bitmap;


/**
 * Created by xihuan22 on 2017/8/27.
 */

public interface BitmapMemoryCache {

    void putBitmap(String key, Bitmap bitmap);

    Bitmap getBitmap(String key);

    void clearMemory(String key);

    void clearAllMemory();

}
