package com.kq.dymicdemo;

import com.kq.ds.SupplierPeiSongSourceFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
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

            dealTableHeader(jasperDesign);
            dealTableDetail(jasperDesign);

            // 编译 and 保存
            compileAndSave(jasperDesign);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void dealTableHeader(JasperDesign jasperDesign) {


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

        JRDesignStaticText staticText = dealJRDesignStaticTextProp(lastWidth, lastX, staticTextTemp);

        System.out.println("");

        childList.add(staticText);

//        childList.add(childList.get(size-1));

    }

    private static JRDesignStaticText dealJRDesignStaticTextProp(int lastWidth, int lastX, JRDesignStaticText staticTextTemp) {
        JRDesignStaticText staticText = new JRDesignStaticText();

        BeanUtils.copyProperties(staticTextTemp,staticText);
        staticText.setX(lastX + lastWidth);
        staticText.setUUID(UUID.randomUUID());

        staticText.getLineBox().setBottomPadding(staticTextTemp.getLineBox().getBottomPadding());
        staticText.getLineBox().setLeftPadding(staticTextTemp.getLineBox().getLeftPadding());
        staticText.getLineBox().getBottomPen().setLineWidth(staticTextTemp.getLineBox().getBottomPen().getLineWidth());
        staticText.getLineBox().getBottomPen().setLineColor(staticTextTemp.getLineBox().getBottomPen().getLineColor());

        staticText.getLineBox().getTopPen().setLineWidth(staticTextTemp.getLineBox().getTopPen().getLineWidth());
        staticText.getLineBox().getTopPen().setLineColor(staticTextTemp.getLineBox().getTopPen().getLineColor());

        staticText.getLineBox().getRightPen().setLineWidth(staticTextTemp.getLineBox().getRightPen().getLineWidth());
        staticText.getLineBox().getRightPen().setLineColor(staticTextTemp.getLineBox().getRightPen().getLineColor());

        return staticText;

//        childList.add(staticText);
    }

    private static void dealTableDetail(JasperDesign jasperDesign) {



        if(jasperDesign.getDetailSection() instanceof JRDesignSection) {
            JRDesignSection designSection = (JRDesignSection)jasperDesign.getDetailSection();

            List<JRBand> bandList = designSection.getBandsList();
            System.out.println(bandList);

            for(JRChild jrChild : bandList) {

                if(jrChild instanceof JRDesignBand) {

                    JRDesignBand designBand = (JRDesignBand)jrChild;

                    List<JRChild> list = designBand.getChildren();

                    int lastWidth = 0;
                    int lastX = 0;

                    JRDesignStaticText staticTextTemp = null;

                    for(JRChild jrChild1 : list) {

                        if (jrChild1 instanceof JRDesignStaticText) {
                            JRDesignStaticText staticText = (JRDesignStaticText) (jrChild1);
                            String text = staticText.getText();

                            int x = staticText.getX();
                            int y = staticText.getY();
                            int width = staticText.getWidth();
                            int height = staticText.getHeight();
                            lastX = x;
                            lastWidth = width;

                            staticTextTemp = staticText;

                            System.out.println("detail text=" + text + " x=" + x + ",y=" + y + ",width=" + width + ",height=" + height);


                        }

                    }

                    JRDesignStaticText staticText = dealJRDesignStaticTextProp(lastWidth, lastX, staticTextTemp);
                    staticText.setText("newDetail");
                    list.add(staticText);
                }
            }





        }else {
            System.out.println("模板有问题..........");
        }


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
