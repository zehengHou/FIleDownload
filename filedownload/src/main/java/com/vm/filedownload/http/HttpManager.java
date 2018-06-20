package com.vm.filedownload.http;

import android.content.Context;

import com.vm.filedownload.file.FileStorageManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpManager {
    private static final int NET_FAIL = 1;
    private Context mContext;

    private OkHttpClient mClient;


    private HttpManager() {
        mClient = new OkHttpClient();
    }

    /*静态单例模式*/
    public static HttpManager getInstance() {
        return Holder.INSTANCE;
    }

    static class Holder {
        public static final HttpManager INSTANCE = new HttpManager();
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * 同步请求
     *
     * @param url
     */
    public Response syncRequest(String url) {
        try {
            return mClient.newCall(new Request.Builder().url(url).build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步请求
     *
     * @param url
     * @param callback
     */
    public void aSyncRequest(final String url, final DownloadCallback callback) {
        mClient.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.fail(0, "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful() && callback != null) {
                    callback.fail(NET_FAIL, "网络请求失败!");
                }
                File file = FileStorageManager.getInstance().getFileByName(url);
                byte[] buffer = new byte[1024 * 500];
                int len;
                FileOutputStream fileOut = new FileOutputStream(file);
                InputStream inputStream = response.body().byteStream();
                while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                    fileOut.write(buffer, 0, len);
                    fileOut.flush();
                }
                callback.success(file);
            }
        });
    }
}
