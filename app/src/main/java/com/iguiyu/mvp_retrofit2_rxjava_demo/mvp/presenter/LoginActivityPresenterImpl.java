package com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.model.Login;
import com.iguiyu.mvp_retrofit2_rxjava_demo.util.ApiServiceUtil;
import com.iguiyu.mvp_retrofit2_rxjava_demo.util.ApiService;
import com.iguiyu.mvp_retrofit2_rxjava_demo.bean.Token;
import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.view.LoginActivityView;
import com.iguiyu.mvp_retrofit2_rxjava_demo.util.ErrorManager;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LoginActivityPresenterImpl extends ErrorManager<Token> implements LoginActivityPresenter {

    private LoginActivityView loginActivityView;
    private static final String TAG = "LoginActivityPresenterI";

    public LoginActivityPresenterImpl(Context context, LoginActivityView loginActivityView) {
        super(context);
        this.loginActivityView = loginActivityView;
    }

    @Override
    public void login(Login login) {
        Log.i(TAG, "login: =================");
        ApiService apiService = ApiServiceUtil.getApiService();
        Observable<Response<Token>> token = apiService.getString(login);
        token.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Token>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted : 停止加载====");
                    }

                    @Override
                    public void onError(Throwable e) {
                        manageError(e);
                    }

                    @Override
                    public void onNext(Response<Token> response) {
                        manageRespones(response);
                    }
                });


    }

    @Override
    public void add(Token token) {
        loginActivityView.saveToken(token);
    }

    @Override
    public void manageErrorMessage(String message) {
        Gson gson = new Gson();
        Login login = gson.fromJson(message, Login.class);
        String errorMobile = login.getMobile();
        String errorPassword = login.getPassword();
        if (errorMobile != null) {
            loginActivityView.showErrorToast(errorMobile);
        }
        if (errorPassword != null) {
            loginActivityView.showErrorToast(errorPassword);
        }
    }
}
