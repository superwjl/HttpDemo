package com.tik.httpdemo;

import android.app.Application;

import com.tik.httpdemo.volley.RequestManager;

public class HttpDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init(this);
    }
}
