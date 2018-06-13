package com.vm.net;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CacheHttp {
    /**
     * main
     *
     * @param args
     */
    public static void main(String args[]) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().cache(new Cache(new File("users/nate/soirce"), 10 * 1024 * 1024)).build();
        Request request = new Request.Builder()
                .url("http://www.qq.com/")
                .build();
        Call newCall = client.newCall(request);
        Response response = newCall.execute();
        System.out.println("net response : " + response.networkResponse());
        System.out.println("cache response : " + response.cacheResponse());

        System.out.println("***********************************************");

        Response response1 = newCall.execute();
        System.out.println("net response 1: " + response1.networkResponse());
        System.out.println("cache response 1: " + response1.cacheResponse());
    }
}
