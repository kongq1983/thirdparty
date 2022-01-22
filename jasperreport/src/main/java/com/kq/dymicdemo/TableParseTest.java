package com.kq.dymicdemo;

import com.kq.ds.SupplierPeiSongSourceFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author kq
 * @date 2022-01-22 15:31
 * @since 2020-0630
 */
public class TableParseTest {

    public static void main(String[] args) {

        String fileName = "table_empty.jrxml";

//        File file = new File("jasperreport/src/main/resources/dymictable",fileName);


        InputStream is = null;
        try {
            // 从资源文件中读取报表

            is = TableParseTest.class.getResourceAsStream("/dymictable/"+fileName);

//            is = TableParseTest.class.getResourceAsStream(file.getAbsolutePath());
            JasperDesign jasperDesign = (JasperDesign) JRXmlLoader.load(is);

            Map styleMap = jasperDesign.getStylesMap();
            // column header 对应的样式
            JRDesignStyle theaderStyle = (JRDesignStyle) styleMap.get("theader");
            // column detail 对应的样式
            JRDesignStyle tbodyStyle = (JRDesignStyle) styleMap.get("tboby");
            // pagefoot 对应的样式
            JRDesignStyle tfootStyle = (JRDesignStyle) styleMap.get("tfoot");

            dealColumn(jasperDesign);

            compileAndSave(jasperDesign);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void dealColumn(JasperDesign jasperDesign) {


        List<JRChild> childList =  jasperDesign.getColumnHeader().getChildren();

        int lastWidth = 0;
        int lastX = 0;
        // width 都一样
        // height 都一样
        // 假设默认都68

        JRDesignStaticText staticTextTemp = null;

        for(JRChild jrChild : childList) {
            if(jrChild instanceof JRDesignStaticText) {
                JRDesignStaticText staticText = (JRDesignStaticText)(jrChild);

                String text = staticText.getText();
                JRLineBox lineBox = staticText.getLineBox();

                int x = staticText.getX();
                int y = staticText.getY();
                int width = staticText.getWidth();
                int height = staticText.getHeight();

                lastX = x;
                lastWidth = width;

                System.out.println("text="+text +" x="+x+",y="+y+",width="+width+",height="+height);

                if(staticText!=null) {
                    staticTextTemp = staticText;
                }

            }
        }

        JRDesignStaticText staticText = new JRDesignStaticText();

        BeanUtils.copyProperties(staticTextTemp,staticText);
        staticText.setX(lastX+lastWidth);
        staticText.setUUID(UUID.randomUUID());

        staticText.getLineBox().setBottomPadding(staticTextTemp.getLineBox().getBottomPadding());
        staticText.getLineBox().setLeftPadding(staticTextTemp.getLineBox().getLeftPadding());
        staticText.getLineBox().getBottomPen().setLineWidth(staticTextTemp.getLineBox().getBottomPen().getLineWidth());
        staticText.getLineBox().getBottomPen().setLineColor(staticTextTemp.getLineBox().getBottomPen().getLineColor());

        staticText.getLineBox().getTopPen().setLineWidth(staticTextTemp.getLineBox().getTopPen().getLineWidth());
        staticText.getLineBox().getTopPen().setLineColor(staticTextTemp.getLineBox().getTopPen().getLineColor());

        staticText.getLineBox().getRightPen().setLineWidth(staticTextTemp.getLineBox().getRightPen().getLineWidth());
        staticText.getLineBox().getRightPen().setLineColor(staticTextTemp.getLineBox().getRightPen().getLineColor());


        childList.add(staticText);

        System.out.println("");



//        childList.add(childList.get(size-1));



    }

    public static void compileAndSave(JasperDesign jasperDesign) throws Exception{
        //            // 编译报表
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            String suffix = "pdf";//pdf格式

//            JasperUtils.prepareReport(jasperReport, type);
            // 报表数据源
            JRDataSource dataSource = new JRBeanCollectionDataSource(SupplierPeiSongSourceFactory.getSupplierPeiSongs());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        String now = LocalDateTime.now().toString().replaceAll("[\\:\\.]","_");
        String outFile = "c:\\print\\test_user_list_"+ now +"."+suffix;
        System.out.println("outFile="+outFile);

        FileUtils.forceMkdir(new File("c:\\print"));

//        File f = new File("D:/excel.xls");
        OutputStream output = new FileOutputStream(outFile);
//        JRXlsExporter exporter = new JRXlsExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,outFile);
//        exporter.exportReport();

        JasperExportManager.exportReportToPdfStream(jasperPrint,output);
        System.out.println("生成成功!");


    }


}
