package com.iguiyu.mvp_retrofit2_rxjava_demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iguiyu.mvp_retrofit2_rxjava_demo.R;
import com.iguiyu.mvp_retrofit2_rxjava_demo.bean.Token;
import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.model.Login;
import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.presenter.LoginActivityPresenterImpl;
import com.iguiyu.mvp_retrofit2_rxjava_demo.mvp.view.LoginActivityView;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    //    private Retrofit retrofit;
    private static final String TAG = "LoginActivity";
    private LinearLayout activityMain;
    private EditText etMobile;
    private EditText etPassword;
    private Button btnNet;
    private LoginActivityPresenterImpl loginActivityPresenter;

    private void assignViews() {
        activityMain = (LinearLayout) findViewById(R.id.activity_main);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnNet = (Button) findViewById(R.id.btn_net);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    public void onClick(View view) {
        //账号和密码
        // mobile：13162553731    password：123456
        String mobile = etMobile.getText().toString();
        String password = etPassword.getText().toString();

        loginActivityPresenter = new LoginActivityPresenterImpl(this, this);
        loginActivityPresenter.login(new Login(mobile, password));

    }

    @Override
    public void saveToken(Token token) {
        Log.i(TAG, "saveToken :Token已经保存： " + token.toString());
        Toast.makeText(this, "成功登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
