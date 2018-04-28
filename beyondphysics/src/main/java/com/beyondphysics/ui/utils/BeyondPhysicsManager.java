package com.beyondphysics.ui.utils;


import com.beyondphysics.network.BitmapRequest;
import com.beyondphysics.network.CacheItemRequest;
import com.beyondphysics.network.Request;
import com.beyondphysics.network.RequestManager;
import com.beyondphysics.network.RequestManagerUseDispatcher;
import com.beyondphysics.network.RequestStatusItem;
import com.beyondphysics.network.cache.CacheItem;
import com.beyondphysics.network.utils.FileTool;
import com.beyondphysics.network.utils.SuperKey;
import com.beyondphysics.ui.BaseActivity;

import java.util.List;

/**
 * Created by xihuan22 on 2017/8/20.
 */

public class BeyondPhysicsManager {

    private static BeyondPhysicsManager beyondPhysicsManager;
    /**
     * NotNull
     */
    private final RequestManager requestManager;


    private BeyondPhysicsManager(BeyondPhysicsManagerParams beyondPhysicsManagerParams) {//只允许单列方式创建
        if (beyondPhysicsManagerParams == null) {
            beyondPhysicsManagerParams = new BeyondPhysicsManagerParams();
        }
        if (beyondPhysicsManagerParams.isUseDispatcher()) {
            this.requestManager = new RequestManagerUseDispatcher(beyondPhysicsManagerParams.getContext(), beyondPhysicsManagerParams.getNetworkThreadCount(), beyondPhysicsManagerParams.getBitmapNetworkThreadCount(), beyondPhysicsManagerParams.getHttpAgreement(), beyondPhysicsManagerParams.getBitmapMemoryCache(), beyondPhysicsManagerParams.getBitmapDiskCacheAnalyze(), beyondPhysicsManagerParams.getDiskCacheThreadCount(), beyondPhysicsManagerParams.getFileCache(), beyondPhysicsManagerParams.getMaxDiskCacheSize(), beyondPhysicsManagerParams.getResponseHandlerType(), beyondPhysicsManagerParams.getDispatcherThreadCount());
        } else {
            this.requestManager = new RequestManager(beyondPhysicsManagerParams.getContext(), beyondPhysicsManagerParams.getNetworkThreadCount(), beyondPhysicsManagerParams.getBitmapNetworkThreadCount(), beyondPhysicsManagerParams.getHttpAgreement(), beyondPhysicsManagerParams.getBitmapMemoryCache(), beyondPhysicsManagerParams.getBitmapDiskCacheAnalyze(), beyondPhysicsManagerParams.getDiskCacheThreadCount(), beyondPhysicsManagerParams.getFileCache(), beyondPhysicsManagerParams.getMaxDiskCacheSize(), beyondPhysicsManagerParams.getResponseHandlerType());
        }
        open();
    }

    /**
     * 已加方法锁####
     * 方法锁保证只会被初始化一次
     */
    public static synchronized BeyondPhysicsManager getInstance(BeyondPhysicsManagerParams beyondPhysicsManagerParams) {
        if (beyondPhysicsManager == null) {
            beyondPhysicsManager = new BeyondPhysicsManager(beyondPhysicsManagerParams);
        }
        return beyondPhysicsManager;
    }


    public RequestManager getRequestManager() {
        return requestManager;
    }

    /**
     * 如果不是RequestManagerUseDispatcher,将抛出异常
     */
    public RequestManagerUseDispatcher getRequestManagerUseDispatcher() {
        RequestManagerUseDispatcher requestManagerUseDispatcher = null;
        if (requestManager instanceof RequestManagerUseDispatcher) {
            requestManagerUseDispatcher = (RequestManagerUseDispatcher) requestManager;
        }
        if (requestManagerUseDispatcher == null) {
            throw new ClassCastException("RequestManagerUseDispatcher不存在");
        }
        return requestManagerUseDispatcher;
    }


    public void open() {
        requestManager.open();
    }

    public void closeByWait() {
        requestManager.closeByWait();
    }

    public void close() {
        requestManager.close();
    }

    public void addRequest(Request<?> request) {
        requestManager.addRequest(request);
    }

    public void addRequestToDispatcher(Request<?> request) {
        getRequestManagerUseDispatcher().addRequestToDispatcher(request);
    }

    public void addRequestWithSort(Request<?> request) {
        requestManager.addRequestWithSort(request);
    }

    public void addRequestWithSortToDispatcher(Request<?> request) {
        getRequestManagerUseDispatcher().addRequestWithSortToDispatcher(request);
    }

    public void addBitmapRequest(BitmapRequest<?> bitmapRequest) {
        requestManager.addBitmapRequest(bitmapRequest);
    }

    public void addBitmapRequestToDispatcher(BitmapRequest<?> bitmapRequest) {
        getRequestManagerUseDispatcher().addBitmapRequestToDispatcher(bitmapRequest);
    }

    public void addBitmapRequestWithSort(BitmapRequest<?> bitmapRequest) {
        requestManager.addBitmapRequestWithSort(bitmapRequest);
    }

    public void addBitmapRequestWithSortToDispatcher(BitmapRequest<?> bitmapRequest) {
        getRequestManagerUseDispatcher().addBitmapRequestWithSortToDispatcher(bitmapRequest);
    }

    public void cancelRequestWithSuperKeyByWait(SuperKey superKey, boolean removeListener) {
        requestManager.cancelRequestWithSuperKeyByWait(superKey, removeListener);
    }

    public void cancelRequestWithSuperKey(SuperKey superKey, boolean removeListener) {
        requestManager.cancelRequestWithSuperKey(superKey, removeListener);
    }

    public void cancelRequestWithSuperKeyToDispatcher(SuperKey superKey, boolean removeListener) {
        getRequestManagerUseDispatcher().cancelRequestWithSuperKeyToDispatcher(superKey, removeListener);
    }

    public void cancelRequestWithRequestByWait(Request<?> request, boolean removeListener) {
        requestManager.cancelRequestWithRequestByWait(request, removeListener);
    }

    public void cancelRequestWithRequest(Request<?> request, boolean removeListener) {
        requestManager.cancelRequestWithRequest(request, removeListener);
    }

    public void cancelRequestWithRequestToDispatcher(Request<?> request, boolean removeListener) {
        getRequestManagerUseDispatcher().cancelRequestWithRequestToDispatcher(request, removeListener);
    }

    public void cancelBitmapRequestWithSuperKeyByWait(SuperKey superKey, boolean removeListener) {
        requestManager.cancelBitmapRequestWithSuperKeyByWait(superKey, removeListener);
    }

    public void cancelBitmapRequestWithSuperKey(SuperKey superKey, boolean removeListener) {
        requestManager.cancelBitmapRequestWithSuperKey(superKey, removeListener);
    }

    public void cancelBitmapRequestWithSuperKeyToDispatcher(SuperKey superKey, boolean removeListener) {
        getRequestManagerUseDispatcher().cancelBitmapRequestWithSuperKeyToDispatcher(superKey, removeListener);
    }

    public void cancelBitmapRequestWithRequestByWait(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        requestManager.cancelBitmapRequestWithRequestByWait(bitmapRequest, removeListener);
    }

    public void cancelBitmapRequestWithRequest(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        requestManager.cancelBitmapRequestWithRequest(bitmapRequest, removeListener);
    }

    public void cancelBitmapRequestWithRequestToDispatcher(BitmapRequest<?> bitmapRequest, boolean removeListener) {
        getRequestManagerUseDispatcher().cancelBitmapRequestWithRequestToDispatcher(bitmapRequest, removeListener);
    }

    public void cancelRequestWithTagByWait(String tag, boolean removeListener) {
        requestManager.cancelRequestWithTagByWait(tag, removeListener);
    }


    public void cancelRequestWithTag(String tag, boolean removeListener) {
        requestManager.cancelRequestWithTag(tag, removeListener);
    }

    public void cancelRequestWithTagToDispatcher(String tag, boolean removeListener) {
        getRequestManagerUseDispatcher().cancelRequestWithTagToDispatcher(tag, removeListener);
    }

    public void cancelAllRequestByWait(boolean removeListener) {
        requestManager.cancelAllRequestByWait(removeListener);
    }

    public void cancelAllRequest(boolean removeListener) {
        requestManager.cancelAllRequest(removeListener);
    }

    public void cancelAllRequestToDispatcher(boolean removeListener) {
        getRequestManagerUseDispatcher().cancelAllRequestToDispatcher(removeListener);
    }


    public RequestStatusItem addRequestStatusItem(int status, int kind, SuperKey superKey) {
        return requestManager.addRequestStatusItem(status, kind, superKey);
    }

    public RequestStatusItem addRequestStatusItem(int status, int kind, String tag) {
        return requestManager.addRequestStatusItem(status, kind, tag);
    }

    public RequestStatusItem addRequestStatusItemWithAll(int status, int kind) {
        return requestManager.addRequestStatusItemWithAll(status, kind);
    }

    public void removeRequestStatusItem(RequestStatusItem requestStatusItem) {
        requestManager.removeRequestStatusItem(requestStatusItem);
    }


    public void clearMemory(String key) {
        requestManager.clearMemory(key);
    }

    public void clearAllMemory() {
        requestManager.clearAllMemory();
    }

    public CacheItem getCacheItemByKey(String key, RequestManager.CacheItemTAG cacheItemTAG) {
        return requestManager.getCacheItemByKey(key, cacheItemTAG);
    }

    public List<CacheItem> getCacheItemsByKeys(List<String> keys, RequestManager.CacheItemTAG cacheItemTAG) {
        return requestManager.getCacheItemsByKeys(keys, cacheItemTAG);
    }

    public void clearCacheItem(String key, RequestManager.CacheItemTAG cacheItemTAG) {
        requestManager.clearCacheItem(key, cacheItemTAG);
    }

    public void clearCacheItems(List<String> keys, RequestManager.CacheItemTAG cacheItemTAG) {
        requestManager.clearCacheItems(keys, cacheItemTAG);
    }

    public void clearAllCacheItem() {
        requestManager.clearAllCacheItem();
    }

    public void doCacheItemsWithCallback(CacheItemRequest cacheItemRequest) {
        getRequestManagerUseDispatcher().doCacheItemsWithCallback(cacheItemRequest);
    }

    public static String getDataFromCache(String url, int requestType, BaseActivity baseActivity) {
        String data = null;
        CacheItem cacheItem = BeyondPhysicsManager.getInstance(baseActivity.getBeyondPhysicsManagerParams()).getCacheItemByKey(RequestManager.getRequestKey(url, requestType), RequestManager.CacheItemTAG.NORMALREQUEST);
        if (cacheItem != null) {
            String cachePath = cacheItem.getCachePath();
            if (cachePath != null) {
                String cacheContent = FileTool.readContentWithLock(cachePath);
                if (cacheContent != null) {
                    data = RequestManager.getDataFromCacheContent(cacheContent);
                }
            }
        }
        return data;
    }
}
