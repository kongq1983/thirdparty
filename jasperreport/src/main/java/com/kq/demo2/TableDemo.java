package com.kq.demo2;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kq
 * @date 2022-01-22 10:02
 * @since 2020-0630
 */
public class TableDemo {

//    public ActionResult projectPrint() {
//        String[] printValue = null;
//        // 从页面中获得要查询的字段
//        String reqPrintValue = getRequest().getParameter("printValue");
//        // 没有选择则默认全打印
//        if (null == reqPrintValue || StringUtils.isEmpty(reqPrintValue)) {
//            printValue = new String[] { "pnumber", "pname", "pdepart", "pdecision", "pthrow", "plastmonth", "pfund", "ploan" };
//        } else {
//            printValue = reqPrintValue.split(",");
//        }
//        // 查询统计数据
//        List<Object[]> projectList = getEntityManager().queryPrintProjectInfo(printValue);
//
//        // 将数据转换为Map对象，换化成Map对象
//        List<Map> reportDataList = new ArrayList<Map>();
//
//        for (int i = 0; i < projectList.size(); i++) {
//            Object[] personStr = projectList.get(i);
//            Map reportData = new HashMap();
//            for (int j = 0; j < personStr.length; j++) {
//                reportData.put("field_" + j, String.valueOf(personStr[j]));
//            }
//            reportDataList.add(reportData);
//        }
//
//        int columCount = 0;// 数据列
//        int fieldCount = 0;// 字段列数（因为pname比较长所以想让pname比其它的列长些，故设计这个变量）
//        int pnameCount = -1;// 记录下pname的序号
//        for (int i = 0; i < printValue.length; i++) {
//            // pthrow下面有两列
//            if ("pthrow".equals(printValue[i])) {
//                columCount = columCount + 2;
//                fieldCount = fieldCount + 2;
//                // ploan下面也有两列
//            } else if ("ploan".equals(printValue[i])) {
//                columCount = columCount + 2;
//                fieldCount = fieldCount + 2;
//                // 故意让pname也占两列
//            } else if ("pname".equals(printValue[i])) {
//                pnameCount = i;// 记录下pname的序号
//                columCount = columCount + 1;
//                fieldCount = fieldCount + 2;
//            } else {
//                // 其它的列都占一个单位
//                columCount = columCount + 1;
//                fieldCount = fieldCount + 1;
//            }
//        }
//
//        InputStream is = null;
//        try {
//            // 从资源文件中读取报表
//            is = this.getClass().getResourceAsStream("/reports/project.jrxml");
//            JasperDesign jasperDesign = (JasperDesign) JRXmlLoader.load(is);
//
//            Map styleMap = jasperDesign.getStylesMap();
//            // column header 对应的样式
//            JRDesignStyle theaderStyle = (JRDesignStyle) styleMap.get("theader");
//            // column detail 对应的样式
//            JRDesignStyle tbodyStyle = (JRDesignStyle) styleMap.get("tboby");
//            // pagefoot 对应的样式
//            JRDesignStyle tfootStyle = (JRDesignStyle) styleMap.get("tfoot");
//
//            int _START_X_ = 20;// x轴的起始位置
//            int startX = _START_X_; // x轴的起始位置
//            // 单列的宽度
//            // 535是jasepreReport报表column最大的宽度
//            int columnWidth = 535 / fieldCount;
//            // 20,24,15是报表中已设置的，一定与之相同
//            final int columnHeadBandHeight = 20;
//            final int detailHeight = 24;
//            final int pagefootHeight = 15;
//
//            // 设置报表字段
//            for (int idx = 0; idx < columCount; idx++) {
//                JRDesignField field = new JRDesignField();
//                field.setName("field_" + idx);
//                field.setValueClass(java.lang.String.class);
//                jasperDesign.addField(field);
//            }
//
//            JRDesignBand columnHeadBand = (JRDesignBand) jasperDesign.getColumnHeader();
//            // 绘制表头
//            for (int idx = 0; idx < printValue.length; idx++) {
//                if ("pnumber".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("序号");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pname".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    // 项目名称的宽度是其它的宽度的2倍
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("项目名称");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth * 2;
//                } else if ("pdepart".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("部门");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pdecision".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("已决策");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pthrow".equals(printValue[idx])) {
//                    // 投审会下面有两列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投审会");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月初");
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX + columnWidth);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月中");
//                    columnHeadBand.addElement(staticText);
//                    startX += 2 * columnWidth;
//                } else if ("plastmonth".equals(printValue[idx])) {
//                    // 投决会下面有一列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投决会");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月下");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pfund".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("基金投资额");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("ploan".equals(printValue[idx])) {
//                    // 投贷协同额下面有两列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投贷协同额");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("金额");
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX + columnWidth);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("入库情况");
//                    columnHeadBand.addElement(staticText);
//                    startX += 2 * columnWidth;
//                }
//            }
//
//            // 绘制Detail部门
//            startX = _START_X_;
//            JRDesignBand columnDetailBand = (JRDesignBand) jasperDesign.getDetail();
//            for (int idx = 0; idx < columCount; idx++) {
//                JRDesignTextField textField = new JRDesignTextField();
//                textField.setStretchWithOverflow(true);
//                textField.setX(startX);
//                textField.setY(0);
//                if (pnameCount == idx) {
//                    textField.setWidth(2 * columnWidth);
//                    startX += 2 * columnWidth;
//                } else {
//                    textField.setWidth(columnWidth);
//                    startX += columnWidth;
//                }
//                textField.setHeight(detailHeight);
//                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
//                textField.setStyle(tbodyStyle);
//                textField.setBlankWhenNull(true);
//                JRDesignExpression expression = new JRDesignExpression();
//                expression.setValueClass(java.lang.String.class);
//                expression.setText("$F{field_" + idx + "}");
//                textField.setExpression(expression);
//                columnDetailBand.addElement(textField);
//            }
//
//            JRDesignBand pageFootBand = (JRDesignBand) jasperDesign.getPageFooter();
//            // 合计数据，本应统计的
//            List<Object[]> pageCountList = new ArrayList<Object[]>();
//            Object[] obj = new String[] { "合计", "15299", "", "", "67121", "92420", "155877", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "XXX小计", "", "24473", "16470", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "WWW小计", "", "7289", "1674", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "ZZZ小计", "", "32700", "13000", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "YYY小计", "", "12733", "120733", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "AAA小计", "", "2225", "120733", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "BBB小计", "", "3000", "0", };
//            pageCountList.add(obj);
//            int footWidth = 535 / 7;
//            for (int p = 0; p < pageCountList.size(); p++) {
//                for (int k = 0; k < 7; k++) {
//                    Object[] ob = pageCountList.get(p);
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(tfootStyle);
//                    staticText.setWidth(footWidth);
//                    staticText.setY(pagefootHeight * p);
//                    staticText.setX(k * footWidth + _START_X_);
//                    staticText.setHeight(pagefootHeight);
//                    staticText.setText(String.valueOf(ob[k]));
//                    pageFootBand.addElement(staticText);
//                }
//            }
//
//            // 编译报表
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            String type = this.getRequest().getParameter("type");//pdf格式
//            JasperUtils.prepareReport(jasperReport, type);
//            // 报表数据源
//            JRDataSource dataSource = new JRBeanCollectionDataSource(reportDataList);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//            HttpServletResponse response = this.getResponse();
//            JasperUtils.export(jasperPrint, response, getRequest(), type);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//    public ActionResult projectPrint() {
//        String[] printValue = null;
//        // 从页面中获得要查询的字段
//        String reqPrintValue = getRequest().getParameter("printValue");
//        // 没有选择则默认全打印
//        if (null == reqPrintValue || StringUtils.isEmpty(reqPrintValue)) {
//            printValue = new String[] { "pnumber", "pname", "pdepart", "pdecision", "pthrow", "plastmonth", "pfund", "ploan" };
//        } else {
//            printValue = reqPrintValue.split(",");
//        }
//        // 查询统计数据
//        List<Object[]> projectList = getEntityManager().queryPrintProjectInfo(printValue);
//
//        // 将数据转换为Map对象，换化成Map对象
//        List<Map> reportDataList = new ArrayList<Map>();
//
//        for (int i = 0; i < projectList.size(); i++) {
//            Object[] personStr = projectList.get(i);
//            Map reportData = new HashMap();
//            for (int j = 0; j < personStr.length; j++) {
//                reportData.put("field_" + j, String.valueOf(personStr[j]));
//            }
//            reportDataList.add(reportData);
//        }
//
//        int columCount = 0;// 数据列
//        int fieldCount = 0;// 字段列数（因为pname比较长所以想让pname比其它的列长些，故设计这个变量）
//        int pnameCount = -1;// 记录下pname的序号
//        for (int i = 0; i < printValue.length; i++) {
//            // pthrow下面有两列
//            if ("pthrow".equals(printValue[i])) {
//                columCount = columCount + 2;
//                fieldCount = fieldCount + 2;
//                // ploan下面也有两列
//            } else if ("ploan".equals(printValue[i])) {
//                columCount = columCount + 2;
//                fieldCount = fieldCount + 2;
//                // 故意让pname也占两列
//            } else if ("pname".equals(printValue[i])) {
//                pnameCount = i;// 记录下pname的序号
//                columCount = columCount + 1;
//                fieldCount = fieldCount + 2;
//            } else {
//                // 其它的列都占一个单位
//                columCount = columCount + 1;
//                fieldCount = fieldCount + 1;
//            }
//        }
//
//        InputStream is = null;
//        try {
//            // 从资源文件中读取报表
//            is = this.getClass().getResourceAsStream("/reports/project.jrxml");
//            JasperDesign jasperDesign = (JasperDesign) JRXmlLoader.load(is);
//
//            Map styleMap = jasperDesign.getStylesMap();
//            // column header 对应的样式
//            JRDesignStyle theaderStyle = (JRDesignStyle) styleMap.get("theader");
//            // column detail 对应的样式
//            JRDesignStyle tbodyStyle = (JRDesignStyle) styleMap.get("tboby");
//            // pagefoot 对应的样式
//            JRDesignStyle tfootStyle = (JRDesignStyle) styleMap.get("tfoot");
//
//            int _START_X_ = 20;// x轴的起始位置
//            int startX = _START_X_; // x轴的起始位置
//            // 单列的宽度
//            // 535是jasepreReport报表column最大的宽度
//            int columnWidth = 535 / fieldCount;
//            // 20,24,15是报表中已设置的，一定与之相同
//            final int columnHeadBandHeight = 20;
//            final int detailHeight = 24;
//            final int pagefootHeight = 15;
//
//            // 设置报表字段
//            for (int idx = 0; idx < columCount; idx++) {
//                JRDesignField field = new JRDesignField();
//                field.setName("field_" + idx);
//                field.setValueClass(java.lang.String.class);
//                jasperDesign.addField(field);
//            }
//
//            JRDesignBand columnHeadBand = (JRDesignBand) jasperDesign.getColumnHeader();
//            // 绘制表头
//            for (int idx = 0; idx < printValue.length; idx++) {
//                if ("pnumber".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("序号");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pname".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    // 项目名称的宽度是其它的宽度的2倍
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("项目名称");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth * 2;
//                } else if ("pdepart".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("部门");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pdecision".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("已决策");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pthrow".equals(printValue[idx])) {
//                    // 投审会下面有两列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投审会");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月初");
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX + columnWidth);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月中");
//                    columnHeadBand.addElement(staticText);
//                    startX += 2 * columnWidth;
//                } else if ("plastmonth".equals(printValue[idx])) {
//                    // 投决会下面有一列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投决会");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("12月下");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("pfund".equals(printValue[idx])) {
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(2 * columnHeadBandHeight);
//                    staticText.setText("基金投资额");
//                    columnHeadBand.addElement(staticText);
//                    startX += columnWidth;
//                } else if ("ploan".equals(printValue[idx])) {
//                    // 投贷协同额下面有两列
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    staticText.setWidth(columnWidth * 2);
//                    staticText.setY(0);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("投贷协同额");
//                    columnHeadBand.addElement(staticText);
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("金额");
//
//                    staticText = new JRDesignStaticText();
//                    staticText.setStyle(theaderStyle);
//                    columnHeadBand.addElement(staticText);
//                    staticText.setWidth(columnWidth);
//                    staticText.setY(columnHeadBandHeight);
//                    staticText.setX(startX + columnWidth);
//                    staticText.setHeight(columnHeadBandHeight);
//                    staticText.setText("入库情况");
//                    columnHeadBand.addElement(staticText);
//                    startX += 2 * columnWidth;
//                }
//            }
//
//            // 绘制Detail部门
//            startX = _START_X_;
//            JRDesignBand columnDetailBand = (JRDesignBand) jasperDesign.getDetail();
//            for (int idx = 0; idx < columCount; idx++) {
//                JRDesignTextField textField = new JRDesignTextField();
//                textField.setStretchWithOverflow(true);
//                textField.setX(startX);
//                textField.setY(0);
//                if (pnameCount == idx) {
//                    textField.setWidth(2 * columnWidth);
//                    startX += 2 * columnWidth;
//                } else {
//                    textField.setWidth(columnWidth);
//                    startX += columnWidth;
//                }
//                textField.setHeight(detailHeight);
//                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
//                textField.setStyle(tbodyStyle);
//                textField.setBlankWhenNull(true);
//                JRDesignExpression expression = new JRDesignExpression();
//                expression.setValueClass(java.lang.String.class);
//                expression.setText("$F{field_" + idx + "}");
//                textField.setExpression(expression);
//                columnDetailBand.addElement(textField);
//            }
//
//            JRDesignBand pageFootBand = (JRDesignBand) jasperDesign.getPageFooter();
//            // 合计数据，本应统计的
//            List<Object[]> pageCountList = new ArrayList<Object[]>();
//            Object[] obj = new String[] { "合计", "15299", "", "", "67121", "92420", "155877", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "XXX小计", "", "24473", "16470", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "WWW小计", "", "7289", "1674", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "ZZZ小计", "", "32700", "13000", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "YYY小计", "", "12733", "120733", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "AAA小计", "", "2225", "120733", };
//            pageCountList.add(obj);
//            obj = new String[] { "", "", "", "BBB小计", "", "3000", "0", };
//            pageCountList.add(obj);
//            int footWidth = 535 / 7;
//            for (int p = 0; p < pageCountList.size(); p++) {
//                for (int k = 0; k < 7; k++) {
//                    Object[] ob = pageCountList.get(p);
//                    JRDesignStaticText staticText = new JRDesignStaticText();
//                    staticText.setStyle(tfootStyle);
//                    staticText.setWidth(footWidth);
//                    staticText.setY(pagefootHeight * p);
//                    staticText.setX(k * footWidth + _START_X_);
//                    staticText.setHeight(pagefootHeight);
//                    staticText.setText(String.valueOf(ob[k]));
//                    pageFootBand.addElement(staticText);
//                }
//            }
//
//            // 编译报表
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            String type = this.getRequest().getParameter("type");//pdf格式
//            JasperUtils.prepareReport(jasperReport, type);
//            // 报表数据源
//            JRDataSource dataSource = new JRBeanCollectionDataSource(reportDataList);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//            HttpServletResponse response = this.getResponse();
//            JasperUtils.export(jasperPrint, response, getRequest(), type);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}
