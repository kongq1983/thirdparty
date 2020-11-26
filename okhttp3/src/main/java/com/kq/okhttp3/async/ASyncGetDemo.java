package com.kq.okhttp3.async;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2020-09-16 9:32
 * @since 2020-0630
 */
public class ASyncGetDemo {

    public static void main(String[] args) throws Exception{

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();

//        String url = "http://localhost:10001/AsyncLongRunningServlet2?time=3000&type=json";
        String url = "http://localhost:10001/async/deferred/100";

        Request request = new Request.Builder().url(url)
                .get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println(response.body().string());

            }
        });
    }

}
