package com.iguiyu.mvp_retrofit2_rxjava_demo.util;

import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.model.Login;
import com.iguiyu.mvp_retrofit2_rxjava_demo.bean.Token;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/21.
 */

public interface ApiService {
    String URL_BASE = "http://parking.iguiyu.com/";

    @POST("login")
    Observable<Response<Token>> getString(@Body Login login);
}
