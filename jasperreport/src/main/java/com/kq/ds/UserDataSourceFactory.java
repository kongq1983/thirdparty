package com.kq.ds;

import com.kq.entity.User;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kq
 * @date 2020-11-26 13:42
 * @since 2020-0630
 */
public class UserDataSourceFactory {

    static Random r = new Random();

    public static List<User> getUserList() {
        return getUserList(150);
    }

    public static List<User> getUserList(int size) {

        List<User> list = new ArrayList<>();

        for(int i=1;i<=size;i++) {
            User user = new User();
            user.setId(i);
            user.setName("测试员");
            user.setAge(r.nextInt(100));
            user.setGender(user.getAge()%2==0?"男":"女");
            user.setNumber(String.valueOf(i));
            user.setBirthday(new Date());
            list.add(user);
        }

        return list;

    }


}
