package com.iguiyu.mvp_retrofit2_rxjava_demo.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * ====================================
 * 作者：Jerry
 * 创建日期：2016/11/21 16:11
 * 描述：
 * ====================================
 */
public class ErrorHandle {

    private String mErrorMessage;
    private Context context;

    private boolean isLogOut;

    private boolean is401;
    private boolean is401s;
    private boolean is404;
    private boolean is500;

    public ErrorHandle(Context context) {
        this.context = context;
        isLogOut = false;
        is401 = false;
        is401s = false;
        is500 = false;
        is404 = false;
    }

    protected void errorDispatcher(int httpCode) {
        if (httpCode == 401) {
            if (!is401) {
                addErrorMessage("登陆信息过期，请重新登陆");
                is401 = true;
            }
            isLogOut = true;
        } else if (httpCode == 404) {
            if (!is404) {
                addErrorMessage("没有找到");
                is404 = true;
            }
        } else if (httpCode > 401 && httpCode < 500) {
            if (!is401s) {
                addErrorMessage("请求有误");
                is401s = true;
            }
        } else if (httpCode > 500) {
            if (!is500) {
                addErrorMessage("无法连接到服务器，请检查网络");
                is500 = true;
            }
        }
    }

    private void logOut() {
//        new DataBase(context).deleteToken();
        Log.i(TAG, "logOut: 登出");
    }

    void addErrorMessage(String message) {
        mErrorMessage = message;
    }

    private void showErrorMessage() {
        Toast.makeText(context, mErrorMessage, Toast.LENGTH_SHORT).show();
    }

    void show() {
        if (isLogOut) {
            logOut();
        } else {
            showErrorMessage();
        }
    }

}
