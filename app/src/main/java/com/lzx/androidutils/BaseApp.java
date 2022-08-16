package com.lzx.androidutils;

import android.app.Application;

import com.lzx.utils.UtilsApplication;

/**
 * @auther 56454
 * @date 2022/8/12
 * @time 16:01.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UtilsApplication.getInstance().init(this);
    }
}
