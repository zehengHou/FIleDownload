package com.vm.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HelloOkHttp {
    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.imooc.com")
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
}
