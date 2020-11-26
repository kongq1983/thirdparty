package com.kq.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class PostDemo1 {

    public static final int size = 2000;

    private static CountDownLatch countDownLatch = new CountDownLatch(size);

    private static Set<Integer> set = new HashSet();
    private static Set<Integer> failSet = new HashSet();

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(PostDemo.JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws Exception{

        String url = "http://localhost:10001/helloworld";
//        String result = doPost(url,null);
//        System.out.println("result="+result);

        Runnable r = ()->{
            try {
               String result =  new PostDemo1().post(url, "{}");
                System.out.println(Thread.currentThread().getName()+"="+result);
            }catch (Exception e){
                failSet.add(Integer.valueOf(Thread.currentThread().getName()));
                System.out.println(Thread.currentThread().getName()+",fail="+e.getMessage());
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        } ;


        for(int i=0;i<size;i++) {
            set.add(i);
            new Thread(r,""+i).start();
        }


        System.out.println("-----------------开始等待--------------------");
        countDownLatch.await();
        System.out.println("-----------------结束等待--------------------");

        System.out.println("失败线程数量="+failSet.size());
        System.out.println("失败线程IDS="+ StringUtils.join(failSet));

    }

}
