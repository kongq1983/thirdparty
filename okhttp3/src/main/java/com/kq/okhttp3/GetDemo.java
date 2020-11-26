package com.kq.okhttp3;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * GetDemo
 *
 * @author kq
 * @date 2019-03-19
 */

public class GetDemo {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static void main1(String[] args) throws IOException {
        GetDemo example = new GetDemo();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

    public static void main(String[] args) throws IOException {
        GetDemo example = new GetDemo();
        String response = example.run("http://172.16.5.12:8080");
        System.out.println(response);
    }
}