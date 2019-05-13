package com.kq.demo;

import com.kq.entity.Order;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

/**
 * PrintOrderPageDemo
 * 打印小票
 * @author kq
 * @date 2019/5/13
 */
public class PrintOrderPageDemo {

    public static void main(String[] args) throws Exception{

        File file = new File("jasperreport/src/main/resources/xiaopiao_page.jasper");
        System.out.println("xiaopiao_nopage.jasper path ="+file.getAbsolutePath());


        String outFile = "c:\\print\\xppage.html";

        FileUtils.forceMkdir(new File("c:\\print"));

        Collection<Order> list = PrintOrderNoPageDemo.getOrderList();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);


//        JasperRunManager.runReportToHtmlFile(file.getAbsoluteFile(),outFile,new HashMap<String,Object>(),list);

        Map<String,Object> map = new HashMap<>();

        JasperRunManager.runReportToHtmlFile(file.getAbsolutePath(),outFile,map,dataSource);

    }




}
