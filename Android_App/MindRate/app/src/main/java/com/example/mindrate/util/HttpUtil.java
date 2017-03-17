package com.example.mindrate.util;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * This class is responsible for network communication using open source framework OkHttp
 * @link http://square.github.io/okhttp/
 *
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/8:23:41</br>
 */

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();


    /**
     * use Okhttp to send http-request
     *
     * @param address  target address
     * @param callback provided by okhttp3
     */
    public static void sendRequestWithOkHttp(String address, okhttp3.Callback callback) {
        //        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * Use OkHttp to post json data to server
     *
     * @param url  server address
     * @param json json data
     * @return the response as string
     * @throws IOException
     */
    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
