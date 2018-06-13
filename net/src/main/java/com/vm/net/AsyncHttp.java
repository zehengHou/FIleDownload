package com.vm.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncHttp {
    /**
     * main
     *
     * @param args
     */
    public static void main(String args[]) {
        sendRequest("http://www.imooc.com");
        sendSyncRequest("http://www.imooc.com");
    }

    public static void sendRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call newCall = client.newCall(request);
        try {
            Response response = newCall.execute();
            if (response.isSuccessful()) {
                System.out.println("response : " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendSyncRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call newCall = client.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse ：" + response.body().string());
            }
        });
    }
}
