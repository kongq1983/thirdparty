package com.kq.drools.test;

import com.kq.drools.entity.Goods;
import com.kq.drools.entity.SaleOrder;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * SaleOrderTest
 *
 * @author kq
 * @date 2019-03-29
 */
public class SaleOrderTest {

    public static void main(String[] args) {
        // 构建KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        // 获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
        KieSession kSession = kieContainer.newKieSession("saleOrderSession");

        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setIndentity(2);
//        saleOrder.setIndentity(1);
        saleOrder.setName("红烧鲫鱼");

        kSession.insert(saleOrder);
        int count = kSession.fireAllRules();
        System.out.println("命中了" + count + "条规则！");
        System.out.println("商品" +saleOrder.getName() + "的商品折扣为" + saleOrder.getDiscount() + "%。");

        kSession.dispose();

    }

}
