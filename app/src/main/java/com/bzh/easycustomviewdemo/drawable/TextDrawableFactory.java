package com.bzh.easycustomviewdemo.drawable;

import android.content.Context;
import android.graphics.Color;

/**
 * ========================================================== <br>
 * <b>版权</b>：　　　音悦台 版权所有(c) 2015 <br>
 * <b>作者</b>：　　　别志华 biezhihua@163.com<br>
 * <b>创建日期</b>：　15-8-21 <br>
 * <b>描述</b>：　　　<br>
 * <b>版本</b>：　    V1.0 <br>
 * <b>修订历史</b>：　<br>
 * ========================================================== <br>
 */
public class TextDrawableFactory {

    private static TextDrawable textDrawabe = null;

    private TextDrawableFactory() {
    }

    public static TextDrawable getDefaultTextDrawable(Context context) {
        if (null == textDrawabe) {
            textDrawabe = new TextDrawable(context);
            textDrawabe.setTextSize(20);
            textDrawabe.setTextColor(Color.BLACK);
            textDrawabe.setText("加载失败!");
        }

        return textDrawabe;
    }



}
