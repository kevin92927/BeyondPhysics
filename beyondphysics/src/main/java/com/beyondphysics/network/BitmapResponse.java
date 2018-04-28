package com.beyondphysics.network;


import android.graphics.Bitmap;

/**
 * Created by xihuan22 on 2017/9/15.
 */

public class BitmapResponse {

    private String urlString;
    private String key;
    private String tag;
    private Bitmap bitmap;
    private int bitmapGetFrom;


    public BitmapResponse() {

    }

    public BitmapResponse(String urlString, String key, String tag, Bitmap bitmap, int bitmapGetFrom) {
        this.urlString = urlString;
        this.key = key;
        this.tag = tag;
        this.bitmap = bitmap;
        this.bitmapGetFrom = bitmapGetFrom;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getBitmapGetFrom() {
        return bitmapGetFrom;
    }

    public void setBitmapGetFrom(int bitmapGetFrom) {
        this.bitmapGetFrom = bitmapGetFrom;
    }

    @Override
    public String toString() {
        return "BitmapResponse{" +
                "urlString='" + urlString + '\'' +
                ", key='" + key + '\'' +
                ", tag='" + tag + '\'' +
                ", bitmap=" + bitmap +
                ", bitmapGetFrom=" + bitmapGetFrom +
                '}';
    }
}
