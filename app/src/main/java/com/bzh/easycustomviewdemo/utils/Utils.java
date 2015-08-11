package com.bzh.easycustomviewdemo.utils;

import android.content.Context;

import com.bzh.easycustomviewdemo.view_1_12.CustomView;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　2015/8/11 14:46 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　   V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class Utils {
    private static final String TAG = "Utils";


    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    public static int getScreenWidth(Context context) {
        if (0 == SCREEN_WIDTH) {
            SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
            SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
        }
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(Context context) {
        if (0 == SCREEN_WIDTH) {
            SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
            SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
        }
        return SCREEN_HEIGHT;
    }
}
