package com.beyondphysics.network.http;

import com.beyondphysics.network.Request;

/**
 * Created by xihuan22 on 2017/8/1.
 */

public interface HttpAgreement {
    /**
     * 继承该接口的网络请求方法不允许返回null,可参考HttpAgreement_Defalt写法,只有当请求是图片请求时才使用bitmapRequestParams参数
     */
    HttpResponse doRequest(Request<?> request, DoRequestParams bitmapRequestParams);
}
