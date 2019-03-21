package com.kq.drools.test;

import com.kq.drools.entity.Goods;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * NoLoopTest
 *
 * @author kq
 * @date 2019-03-21
 */
public class NoLoopTest {

    public static void main(String[] args) {
        // 构建KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        // 获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
        KieSession kSession = kieContainer.newKieSession("noLoopSession");

        Goods product = new Goods();
        product.setType(1);
        product.setDiscount(10);

        kSession.insert(product);
        int count = kSession.fireAllRules();
        System.out.println("命中了" + count + "条规则！");
        System.out.println("商品" +product.getType() + "的商品折扣为" + product.getDiscount() + "%。");

        kSession.dispose();

    }

}
