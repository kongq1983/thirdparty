package com.kq.okhttp3;

import com.alibaba.fastjson.JSONObject;
import com.kq.okhttp3.util.OkHttpUtil;

/**
 * LoginPostDemo
 *
 * @author kq
 * @date 2019-03-19
 */
public class LoginPostDemo {



    public static void main(String args[]) throws Exception{
        String url = "http://www.roundsapp.com/post";
        String json = PostDemo.bowlingJson("wang","king");
        String result = OkHttpUtil.post(url,json);

        System.out.printf(" result=%s \n",result);

    }



}
