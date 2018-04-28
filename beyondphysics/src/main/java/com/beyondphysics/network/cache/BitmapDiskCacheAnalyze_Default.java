package com.beyondphysics.network.cache;


import android.graphics.Bitmap;

import com.beyondphysics.network.BitmapRequest;
import com.beyondphysics.network.RequestManager;
import com.beyondphysics.network.http.DoRequestParams;
import com.beyondphysics.network.http.HttpResponse;
import com.beyondphysics.network.utils.FileNameLockManager;
import com.beyondphysics.network.utils.FileTool;
import com.beyondphysics.network.utils.UrlTool;


/**
 * Created by xihuan22 on 2017/9/7.
 */

public class BitmapDiskCacheAnalyze_Default implements BitmapDiskCacheAnalyze {

    @Override
    public HttpResponse doDiskCacheAnalyze(BitmapRequest<?> bitmapRequest, DoRequestParams doRequestParams) {
        HttpResponse httpResponse = new HttpResponse();
        if (bitmapRequest == null) {
            httpResponse.setStatus(HttpResponse.ERROR);
            httpResponse.setResult(HttpResponse.ERROR_UNKNOWN);
            FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "BitmapDiskCacheAnalyze_Defalt_doDiskCacheAnalyze:bitmapRequest参数为null", null, 1);
        } else {
            if (bitmapRequest.isCancelRequest()) {
                httpResponse.setStatus(HttpResponse.CANCEL);
                httpResponse.setResult(HttpResponse.CANCEL_TIPS);
            } else {
                String urlString = bitmapRequest.getUrlString();
                int type = UrlTool.getUrlType(urlString);
                if (type == UrlTool.URLTYPE_HTTP || type == UrlTool.URLTYPE_HTTPS) {
                    String fileName = bitmapRequest.getCachePath();
                    if (fileName != null) {
                        try {
                            FileNameLockManager.getInstance().getFileNameLock(fileName);
                            try {
                                Bitmap bitmap = FileTool.getCompressBitmapFromFile(fileName, bitmapRequest.getWidth(), bitmapRequest.getHeight(), bitmapRequest.getScaleType(), bitmapRequest.getBitmapConfig());
                                if (bitmap == null) {
                                    httpResponse.setStatus(HttpResponse.LOST);
                                    httpResponse.setResult(HttpResponse.LOST_TIPS);
                                    FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "BitmapDiskCacheAnalyze_Defalt_doDiskCacheAnalyze:File资源解析失败", null, 1);
                                } else {
                                    httpResponse.setStatus(HttpResponse.SUCCESS);
                                    httpResponse.setResult(BitmapRequest.BITMAPREADDISKCACHESUCCESS);
                                    httpResponse.setBitmapGetFrom(HttpResponse.BITMAP_GETFROM_FILE);
                                    httpResponse.setBitmap(bitmap);
                                }
                            } catch (OutOfMemoryError e) {
                                e.printStackTrace();
                                httpResponse.setStatus(HttpResponse.ERROR);
                                httpResponse.setResult(HttpResponse.ERROR_OUTOFMEMORY);
                                FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "HttpAgreement_Defalt_doRequest:OutOfMemoryError", e, 1);
                            }
                        } finally {
                            FileNameLockManager.getInstance().removeFileNameLock(fileName);
                        }
                    } else {
                        httpResponse.setStatus(HttpResponse.CACHE_PATH_ERROR);
                        httpResponse.setResult(HttpResponse.CACHE_PATH_ERROR_TIPS);
                    }
                } else if (type == UrlTool.URLTYPE_FILE) {
                    String fileName = UrlTool.getFileNameFromUrl(urlString);
                    if (fileName != null) {
                        try {
                            FileNameLockManager.getInstance().getFileNameLock(fileName);
                            try {
                                Bitmap bitmap = FileTool.getCompressBitmapFromFile(fileName, bitmapRequest.getWidth(), bitmapRequest.getHeight(), bitmapRequest.getScaleType(), bitmapRequest.getBitmapConfig());
                                if (bitmap == null) {
                                    httpResponse.setStatus(HttpResponse.LOST);
                                    httpResponse.setResult(HttpResponse.LOST_TIPS);
                                    FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "BitmapDiskCacheAnalyze_Defalt_doDiskCacheAnalyze:File资源解析失败", null, 1);
                                } else {
                                    httpResponse.setStatus(HttpResponse.SUCCESS);
                                    httpResponse.setResult(BitmapRequest.BITMAPREADDISKCACHESUCCESS);
                                    httpResponse.setBitmapGetFrom(HttpResponse.BITMAP_GETFROM_FILE);
                                    httpResponse.setBitmap(bitmap);
                                }
                            } catch (OutOfMemoryError e) {
                                e.printStackTrace();
                                httpResponse.setStatus(HttpResponse.ERROR);
                                httpResponse.setResult(HttpResponse.ERROR_OUTOFMEMORY);
                                FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "HttpAgreement_Defalt_doRequest:OutOfMemoryError", e, 1);
                            }
                        } finally {
                            FileNameLockManager.getInstance().removeFileNameLock(fileName);
                        }
                    } else {
                        httpResponse.setStatus(HttpResponse.CACHE_PATH_ERROR);
                        httpResponse.setResult(HttpResponse.CACHE_PATH_ERROR_TIPS);
                    }
                } else if (type == UrlTool.URLTYPE_ASSETS) {
                    String assetsName = UrlTool.getAssetsNameFromUrl(urlString);
                    if (assetsName != null) {
                        try {
                            Bitmap bitmap = FileTool.getCompressBitmapFromAssets(bitmapRequest.getContext(), assetsName, bitmapRequest.getWidth(), bitmapRequest.getHeight(), bitmapRequest.getScaleType(), bitmapRequest.getBitmapConfig());
                            if (bitmap == null) {
                                httpResponse.setStatus(HttpResponse.LOST);
                                httpResponse.setResult(HttpResponse.LOST_TIPS);
                                FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "BitmapDiskCacheAnalyze_Defalt_doDiskCacheAnalyze:Assets资源解析失败", null, 1);
                            } else {
                                httpResponse.setStatus(HttpResponse.SUCCESS);
                                httpResponse.setResult(BitmapRequest.BITMAPREADDISKCACHESUCCESS);
                                httpResponse.setBitmapGetFrom(HttpResponse.BITMAP_GETFROM_ASSETS);
                                httpResponse.setBitmap(bitmap);
                            }
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                            httpResponse.setStatus(HttpResponse.ERROR);
                            httpResponse.setResult(HttpResponse.ERROR_OUTOFMEMORY);
                            FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "HttpAgreement_Defalt_doRequest:OutOfMemoryError", e, 1);
                        }
                    } else {
                        httpResponse.setStatus(HttpResponse.CACHE_PATH_ERROR);
                        httpResponse.setResult(HttpResponse.CACHE_PATH_ERROR_TIPS);
                    }
                } else if (type == UrlTool.URLTYPE_RESOURCE) {
                    int resourceId = UrlTool.getResourceIdFromUrl(urlString);
                    try {
                        Bitmap bitmap = FileTool.getCompressBitmapFromResource(bitmapRequest.getContext(), resourceId, bitmapRequest.getWidth(), bitmapRequest.getHeight(), bitmapRequest.getScaleType(), bitmapRequest.getBitmapConfig());
                        if (bitmap == null) {
                            httpResponse.setStatus(HttpResponse.LOST);
                            httpResponse.setResult(HttpResponse.LOST_TIPS);
                            FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "BitmapDiskCacheAnalyze_Defalt_doDiskCacheAnalyze:Resource资源解析失败", null, 1);
                        } else {
                            httpResponse.setStatus(HttpResponse.SUCCESS);
                            httpResponse.setResult(BitmapRequest.BITMAPREADDISKCACHESUCCESS);
                            httpResponse.setBitmapGetFrom(HttpResponse.BITMAP_GETFROM_RESOURCE);
                            httpResponse.setBitmap(bitmap);
                        }
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        httpResponse.setStatus(HttpResponse.ERROR);
                        httpResponse.setResult(HttpResponse.ERROR_OUTOFMEMORY);
                        FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "HttpAgreement_Defalt_doRequest:OutOfMemoryError", e, 1);
                    }
                } else {
                    httpResponse.setStatus(HttpResponse.ERROR);
                    httpResponse.setResult(HttpResponse.ERROR_URLTYPE);
                    FileTool.needShowAndWriteLogToSdcard(RequestManager.openDebug, RequestManager.logFileName, "HttpAgreement_Defalt_doRequest:type不存在", null, 1);
                }
            }
        }
        return httpResponse;
    }

}
