package com.kq.ds;

import com.kq.entity.Company;
import com.kq.entity.SupplierPeiSong;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kq
 * @date 2022-01-22 14:05
 * @since 2020-0630
 */
public class SupplierPeiSongSourceFactory {


    public static List<SupplierPeiSong> getSupplierPeiSongs(){

        List<SupplierPeiSong> companyList = new ArrayList<>();

        for(int i=1;i<=20;i++) {
            SupplierPeiSong c = new SupplierPeiSong();
            c.setId(String.valueOf(i));
            c.setName("菜品"+i);
           c.setApproveDate("2022-01-01");
           c.setPerson("小张"+i);

           String[] str = new String[3];
            for(int j=0;j<str.length;j++) {
                str[j] =  j+"";
            }

            c.setDataArray(str);

            companyList.add(c);
        }


        return companyList;
    }


}
