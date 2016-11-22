package com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.model;

/**
 * Created by Administrator on 2016/11/21.
 */

public class Login {
    private String mobile;
    private String password;

    public Login(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
