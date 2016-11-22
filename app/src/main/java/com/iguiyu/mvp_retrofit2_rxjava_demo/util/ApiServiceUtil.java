package com.iguiyu.mvp_retrofit2_rxjava_demo.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ApiServiceUtil {
    public static ApiService getApiService() {

        return new Retrofit.Builder()
                .baseUrl(ApiService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
