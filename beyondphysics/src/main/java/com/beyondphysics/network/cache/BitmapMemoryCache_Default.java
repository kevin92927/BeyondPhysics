package com.beyondphysics.network.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 * Created by xihuan22 on 2017/8/27.
 */

public class BitmapMemoryCache_Default implements BitmapMemoryCache {
    /**
     * NotNull
     */
    private final LruCache<String, Bitmap> lruCache;


    public BitmapMemoryCache_Default() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int maxSize = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        if (key == null || bitmap == null) {
            return;
        }
        lruCache.put(key, bitmap);

    }

    @Override
    public Bitmap getBitmap(String key) {
        if (key == null) {
            return null;
        }
        return lruCache.get(key);
    }

    @Override
    public void clearMemory(String key) {
        if (key == null) {
            return;
        }
        lruCache.remove(key);
    }

    @Override
    public void clearAllMemory() {
        lruCache.evictAll();
    }
}
