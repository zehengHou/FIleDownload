package com.vm.okhttp3;

import android.app.Application;

import com.vm.filedownload.file.FileStorageManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(this);
    }
}
