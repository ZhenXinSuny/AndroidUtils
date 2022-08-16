package com.lzx.utils;

import android.content.Context;

/**
 * @auther 56454
 * @date 2022/8/12
 * @time 16:00.
 */
public class UtilsApplication {

    private static volatile UtilsApplication mInstance = null;

    /**
     * ApplicationContext
     */
    private Context mApplicationContext;



    private UtilsApplication() {

    }

    /**
     * 获取 CubeUI 单例对象
     *
     * @return
     */
    public static UtilsApplication getInstance() {
        if (mInstance == null) {
            synchronized (UtilsApplication.class) {
                if (mInstance == null) {
                    mInstance = new UtilsApplication();
                }
            }
        }
        return mInstance;
    }
    public void init(Context applicationContext) {
        this.mApplicationContext = applicationContext;
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public Context getContext() {
        return this.mApplicationContext;
    }

}
