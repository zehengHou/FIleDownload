package com.vm.okhttp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vm.filedownload.file.FileStorageManager;
import com.vm.filedownload.http.DownloadCallback;
import com.vm.filedownload.http.HttpManager;
import com.vm.filedownload.utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = FileStorageManager.getInstance().getFileByName("http://www.imooc.com");
        Logger.debug("hzh", "file path = " + file.getAbsolutePath());
        HttpManager.getInstance().aSyncRequest("https://baike.baidu.com/pic/%E5%BD%B1%E6%B5%81%E4%B9%8B%E4%B8%BB/6302550/0/5ab5c9ea15ce36d398d3b29332f33a87e850b1b9?fr=lemma&ct=single#aid=0&pic=5ab5c9ea15ce36d398d3b29332f33a87e850b1b9", new DownloadCallback() {
            @Override
            public void success(File file) {

            }

            @Override
            public void fail(int errorCode, String errorMessage) {

            }

            @Override
            public void progress(int progress) {

            }
        });
    }
}
