package com.kq.dymicdemo;

import com.kq.ds.SupplierPeiSongSourceFactory;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kq
 * @date 2020-12-04 10:32
 * @since 2020-0630
 */
public class TestDymicTableDemo {


    public static void main(String[] args) throws Exception{
        String fileName = "table_empty.jasper";
//
        boolean isExportPdf = true;
//        boolean isExportPdf = false;

        File file = new File("jasperreport/src/main/resources/dymictable",fileName);
        System.out.println(fileName+".jasper path ="+file.getAbsolutePath());

        String suffix = "html";
        if(isExportPdf) suffix="pdf";

        String now = LocalDateTime.now().toString().replaceAll("[\\:\\.]","_");

        String outFile = "c:\\print\\dymic_table_list_"+ now +"."+suffix;
        System.out.println("outFile="+outFile);

        FileUtils.forceMkdir(new File("c:\\print"));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(SupplierPeiSongSourceFactory.getSupplierPeiSongs());


//        JasperRunManager.runReportToHtmlFile(file.getAbsoluteFile(),outFile,new HashMap<String,Object>(),list);

        Map<String,Object> map = new HashMap<>();

        if(isExportPdf){
//            JasperRunManager.runReportToPdf(file.getAbsolutePath(),outFile,map,dataSource);
            JasperRunManager.runReportToPdfFile(file.getAbsolutePath(),outFile,map,dataSource);
        }else {
            JasperRunManager.runReportToHtmlFile(file.getAbsolutePath(),outFile,map,dataSource);
        }

    }

    public static void main2(String[] args) {
        String now = LocalDateTime.now().toString().replaceAll("[\\:\\.]","_");
        String now1 = LocalDateTime.now().toString().replaceAll("\\:","_");
        System.out.println(now);
        System.out.println(now1);
    }



}
