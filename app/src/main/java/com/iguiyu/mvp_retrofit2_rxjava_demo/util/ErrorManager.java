package com.iguiyu.mvp_retrofit2_rxjava_demo.util;

import android.content.Context;

import java.io.IOException;

import retrofit2.Response;

/**
 * ====================================
 * 作者：Jerry
 * 创建日期：2016/11/21 16:11
 * 描述：错误管理
 * ====================================
 */
public abstract class ErrorManager<T> extends ErrorHandle {

    private Context mContext;

    private boolean error;

    public ErrorManager(Context context) {
        super(context);
        this.mContext = context;
        error = false;
    }


    public abstract void add(T token);

    public abstract void manageErrorMessage(String message);

    /**
     * 需要重写的方法
     *
     * @param throwable
     */
    protected void manageError(Throwable throwable) {
        throwable.printStackTrace();
        addErrorMessage("网络有误");
        error = true;
        showError();
    }

    /**
     * 需要重写的方法
     *
     * @param response
     */
    protected void manageRespones(Response<T> response) {
        if (response.isSuccessful()) {
            add(response.body());
        } else {
            if (response.code() == 400) {
                try {
                    manageErrorMessage(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorDispatcher(response.code());
                error = true;
            }
        }
        showError();
    }

    private void showError() {
        if (error) {
            show();
        }
    }
}
