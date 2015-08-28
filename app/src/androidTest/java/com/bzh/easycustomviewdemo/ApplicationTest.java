package com.bzh.easycustomviewdemo;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.io.File;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        String dir = "/home/biezhihua/图片";
        File file = new File(dir);
        String fileName[] = file.list();
        int number = fileName.length;
        File newFile[] = new File[number];

        System.out.println("number = " + number);
        for(int i=0; i<number; i++) {
            System.out.println((i+1) + ":" + fileName[i]);
            newFile[i] = new File(dir+fileName[i]);
        }
        System.out.println("============分割线=============");
        for(int i=0; i<number;i++){
            boolean flag = newFile[number - (i+1)].renameTo(new File(dir + ("biezhihua"+i+1)+".jpg"));
            if(flag){
                System.out.println("重命名成功"+(i+1));
            } else {
                System.out.println("失败");
            }
        }

    }
}