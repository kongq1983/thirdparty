package com.kq.demo2;

/**
 * https://blog.csdn.net/easyj2ee/article/details/83139895
 * @author kq
 * @date 2022-01-22 10:47
 * @since 2020-0630
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//import org.apache.commons.lang.ArrayUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 使用jasperReport做报表时的工具支持类.有两个用途,生成jasperPrint对象,和设置导出时的session
 *
 * @author yaer
 * @date 2006-8-26
 * @modify date 2006-12-8
 */
public class ReportUtils {
    public static final String XLS = "xls";// 导出为xls文件;
    public static final String PDF = "pdf";// 导出为pdf文件;
    private static final String JASPER = "jasper";// 编译过后的报表文件;
    private static final String JRXML = "jrxml";// 原始的报表文件(xml格式);
//    private HttpServletRequest request;
//    private HttpSession session;
    private String rootPath;// 报表文件路径

    /**
     * 在jsf环境下时构造些工具类对象
     *
     * @param context
     *            jsf的上下文对象
     */

//    public ReportUtils(FacesContext context) {
//        request = (HttpServletRequest) context.getExternalContext()
//                .getRequest();
//        session = (HttpSession) context.getExternalContext().getSession(true);
//        this.createRootPath(request);// 生成报表文件的绝对路径
//    }
//    /**
//     * 在其它web环境下构造此工具类对象
//     *
//     * @param req
//     *            request请求对象
//     */
//    public ReportUtils(HttpServletRequest req) {
//        this.request = req;
//        this.session = req.getSession();
//        this.createRootPath(request);// 生成报表文件的绝对路径
//    }
    /**
     * 获得报表文件的绝对路径
     *
     * @return rootPath
     */
    public String getRootPath() {
        return rootPath;
    }
    /**
     * 获得JasperPrint对象; jasperPrint对象在jasperReport中是填充了报表数据后的一个实体,打印,导出,显示都要使用它.
     * 此方法含有java5.0支持的'可变参数'特性.params其实质是一个对象数组.在调用些方法时要注意它可能的参数顺序.
     * 此方法参数描述:
     * 1、最多只有四个参数。
     * 2、固定参数filePath表示报表文件的路径,为了支持drp系统中动态尺码组做表头的特性, filePath包括两类:
     *    编译过后的文件扩展名为'.jasper'和未编译的原始xml文件'.jrxml';
     *    若报表中有动态尺码组作表头，则filePath为扩展名是'.jrxml'的文件。
     *    若报表中不涉及动态尺码组,则filePath为扩展名是'.jasper'的文件。
     * 3、可变参数params的完整列表是(注意顺序):Object obj/Collection dataSource,String seprator,String[][] sizeGroup.
     *    这三个参数中,有一个例外,Object obj/Collection dataSource必须有一个，此参数表示填充报表的数据,可以是一个Collection式的集合,
     *    也可以是一个model对象(有且只有一个Collection的属性);
     *    String seprator表示分隔符,如果数据源是一个Array的集合,则需此参数。String[][]sizeGroup表款尺码组的二维数组。
     *
     * @param filePath
     * @param params
     * @return jasperPrint
     */
//    public JasperPrint getJasperPrint(String filePath, Object... params) {
//        JasperReport jasperReport = null;
//        try {
//            if (JASPER.equals(filePath.substring(filePath.indexOf(".") + 1,
//                    filePath.length()))) {// jasper式文件的处理
//                jasperReport = getReportTemplate(filePath);
//            }
//            if (JRXML.equals(filePath.substring(filePath.indexOf(".") + 1,
//                    filePath.length()))) {// jrxml式文件的处理
//                jasperReport = ReportDesignProcess.getJasperReport(filePath,
//                        (String[][]) params[params.length - 1]);// 重新设置表头,编译
//                params = ArrayUtils.remove(params, params.length - 1);// 删除参数中的sizeGroup
//            }
//            return fillReport(jasperReport, params);
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    /**
     * 获得JasperPrint对象;自定义填充报表时的parameter和dataSource. 参数说明和动态表头的用法参考上一方法
     * @param filePath
     * @param parameter
     * @param dataSource
     * @param sizeGroup
     * @return
     */
//    public JasperPrint getJasperPrint(String filePath, Map parameter,
//                                      JRDataSource dataSource, Object... sizeGroup) {
//        JasperReport jasperReport = null;
//        try {
//            if (sizeGroup.length == 0) {
//                jasperReport = getReportTemplate(filePath);
//            }
//            if (sizeGroup.length == 1) {
//                jasperReport = ReportDesignProcess.getJasperReport(filePath,
//                        (String[][]) sizeGroup[sizeGroup.length - 1]);// 重新设置表头,编译
//            }
//            return JasperFillManager.fillReport(jasperReport, parameter,
//                    dataSource);
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void setAttrToPage(JasperPrint jasperPrint, String report_fileName,
//                              String report_type) {
//        session.setAttribute("REPORT_JASPERPRINT", jasperPrint);
//        session.setAttribute("REPORT_FILENAME", report_fileName);
//        session.setAttribute("REPORT_TYPE", report_type);
//    }
//    private JasperPrint fillReport(JasperReport jasperReport, Object[] params)
//            throws JRException {
//        Map parameters = null;
//        JRDataSource ds = null;
//        if (params.length == 0) {
//            return null;
//        }
//        if (params.length == 1 && params[0].getClass() == ArrayList.class) {//其实质是要判断是否是集合
//            ds = new JRBeanCollectionDataSource((Collection) params[0]);
//        }
//        if (params.length == 1 && params[0].getClass() != ArrayList.class) {
//            ClassAnalysis ca = new ClassAnalysis(params[0]);
//            parameters = ca.getFields();
//            ds = new JRBeanCollectionDataSource(ca.getSet());
//        }
//        if (params.length == 2 && params[0].getClass() == ArrayList.class) {
//            ds = new JRArrayCollectionDataSource((Collection) params[0],
//                    params[1].toString());
//        }
//        if (params.length == 2 && params[0].getClass() != ArrayList.class) {
//            ClassAnalysis ca = new ClassAnalysis(params[0]);
//            parameters = ca.getFields();
//            ds = new JRArrayCollectionDataSource(ca.getSet(), params[1]
//                    .toString());
//        }
//        return JasperFillManager.fillReport(jasperReport,
//                parameters == null ? new HashMap() : parameters, ds);
//    }
//    private void createRootPath(HttpServletRequest request) {
//        rootPath = request.getSession().getServletContext().getRealPath("/")
//                + "WEB-INF\\classes\\com\\webstone\\drp\\report\\jaser\\";
//    }
//    private JasperReport getReportTemplate(String jasperPath)
//            throws JRException {
//        return (JasperReport) JRLoader.loadObject(rootPath + jasperPath);
//    }
}