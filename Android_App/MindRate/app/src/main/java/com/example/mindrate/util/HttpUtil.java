package com.example.mindrate.util;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Project: MindRate
 * Package: com.example.mindrate.util
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:41
 */

public class HttpUtil {

    public static void uploadAnswerToServer() {

    }

    /**
     * use Okhttp to send http-request
     *
     * @param address target address
     * @param callback provided by okhttp3
     */
    public static void sendRequestWithOkHttp(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
