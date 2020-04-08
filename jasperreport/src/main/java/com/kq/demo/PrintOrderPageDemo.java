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

        //0: xiaopiao_page.jasper
        //1: xiaopiao_page_height.jasper
        int index = 1;
        String fileName = getFileName(index);
//
//        boolean isExportPdf = true;
        boolean isExportPdf = false;

        File file = new File("jasperreport/src/main/resources",fileName);
        System.out.println(fileName+".jasper path ="+file.getAbsolutePath());

        String suffix = "html";
        if(isExportPdf) suffix="pdf";

        String outFile = "c:\\print\\xppage_"+index+"."+suffix;

        FileUtils.forceMkdir(new File("c:\\print"));

        Collection<Order> list = PrintOrderNoPageDemo.getOrderList();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);


//        JasperRunManager.runReportToHtmlFile(file.getAbsoluteFile(),outFile,new HashMap<String,Object>(),list);

        Map<String,Object> map = new HashMap<>();

        if(isExportPdf){
//            JasperRunManager.runReportToPdf(file.getAbsolutePath(),outFile,map,dataSource);
            JasperRunManager.runReportToPdfFile(file.getAbsolutePath(),outFile,map,dataSource);
        }else {
            JasperRunManager.runReportToHtmlFile(file.getAbsolutePath(),outFile,map,dataSource);
        }



    }


    public static String getFileName(int index) {
        if(index==0){
            return "xiaopiao_page.jasper";
        }

        return "xiaopiao_page_height.jasper";
    }


}
