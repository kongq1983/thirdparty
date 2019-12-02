package com.kq.rxjava3;

import com.kq.rxjava3.entity.Inventory;
import com.kq.rxjava3.entity.InventoryDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * DataUtil
 *
 * @author kq
 * @date 2019-12-02
 */
public class DataUtil {

    public static final List<Inventory> getInventoys() {

        List<Inventory> list = new ArrayList<>();
        for(long i=1;i<10;i++) {
            Inventory inventory = new Inventory();
            inventory.setId(i);
            inventory.setName("king"+i);
            inventory.setCode("000"+i);
            list.add(inventory);
        }

        return list;

    }

    public static List<InventoryDetail> getInventoryDetails(Long inventoryId) {

        List<InventoryDetail> list = new ArrayList<>();
        if(inventoryId%2==0) {
            for(int i=1;i<=2;i++) {
                InventoryDetail detail = new InventoryDetail();
                detail.setId(inventoryId+i);
                detail.setColor("red");
                list.add(detail);
            }
        } else {
            InventoryDetail detail = new InventoryDetail();
            detail.setId(inventoryId+1);
            detail.setColor("blue");
            list.add(detail);
        }

        return list;

    }

}
