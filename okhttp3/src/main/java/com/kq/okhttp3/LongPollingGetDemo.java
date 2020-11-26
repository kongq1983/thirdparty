package com.kq.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2020-09-15 17:34
 * @since 2020-0630
 */
public class LongPollingGetDemo {

    //    OkHttpClient client = new OkHttpClient();
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
            .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
            .build();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static void main(String[] args) throws IOException {
        LongPollingGetDemo example = new LongPollingGetDemo();
        String response = example.run("http://localhost:10001/longpolling/view/1?sync=0");
        System.out.println("response:" + response);
    }

    public static void main2(String[] args) throws Exception {
        LongPollingGetDemo example = new LongPollingGetDemo();
        String response = example.run("http://localhost:10001/AsyncLongRunningServlet2?time=3000&type=json");
        System.out.println("response:" + response);

        TimeUnit.SECONDS.sleep(100);
    }
}
