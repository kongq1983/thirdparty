package com.kq.demo;

import com.kq.entity.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * PrintOrderDemo
 * 打印小票(没有分页)
 * @author kq
 * @date 2019/5/13
 */
public class PrintOrderNoPageDemo {

    public static void main(String[] args) throws Exception{

        File file = new File("jasperreport/src/main/resources/xiaopiao_nopage.jasper");
        System.out.println("xiaopiao_nopage.jasper path ="+file.getAbsolutePath());


        String outFile = "c:\\print\\xpnopage.html";

        FileUtils.forceMkdir(new File("c:\\print"));

        Collection<Order> list = getOrderList();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);


//        JasperRunManager.runReportToHtmlFile(file.getAbsoluteFile(),outFile,new HashMap<String,Object>(),list);

        Map<String,Object> map = new HashMap<>();

        JasperRunManager.runReportToHtmlFile(file.getAbsolutePath(),outFile,map,dataSource);

    }


    public static List<Order> getOrderList(){

        List<Order> list = new ArrayList<>();
        for(int i=0;i<150;i++){
            Order o = new Order();
            o.setName("name"+i);
            o.setAmount(i);
            list.add(o);
        }

        return list;
    }

}
