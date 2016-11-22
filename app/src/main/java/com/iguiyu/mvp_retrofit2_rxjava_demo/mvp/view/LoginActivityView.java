package com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.view;

import com.iguiyu.mvp_retrofit2_rxjava_demo.bean.Token;

/**
 * Created by Administrator on 2016/11/21.
 */

public interface LoginActivityView extends BaseView {
    void saveToken(Token token);
}
