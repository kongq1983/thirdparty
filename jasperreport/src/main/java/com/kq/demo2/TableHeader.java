package com.kq.demo2;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.util.Collection;
import java.util.Map;

/**
 * @author kq
 * @date 2022-01-22 10:21
 * @since 2020-0630
 */
public class TableHeader {

    /**
     * @Title: dynamiccolumn
     * @Description: TODO(对design进行处理，去掉不应该显示的列)
     * @param @param jdesign JasperDesign
     * @param @param params 需要显示的列
     * @return JasperDesign 返回类型
     */
    public static JasperDesign dynamiccolumn(JasperDesign jdesign, Map params) {
        /*
         * 该方法目前仅进行了简单的处理，如需更多业务，且自行添加 比如：1. 修改元素的位置 2.自动调整Title的宽度
         * 3.自行调整整个报表的宽度
         */
        Collection dynamiccolumns = (Collection) params.get("dynamiccolumn");
        if (dynamiccolumns != null) {

            JRDesignBand cHeader = (JRDesignBand) jdesign.getColumnHeader();
            JRBand cDetailBand = jdesign.getDetailSection().getBands()[0];
            JRDesignBand cDetail = null;
            if (cDetailBand != null && cDetailBand instanceof JRDesignBand) {
                cDetail = (JRDesignBand) cDetailBand;
            }
            JRElement[] es_header = cHeader.getElements();
            assert cDetail != null;
            JRElement[] es_detail = cDetail.getElements();
            for (int i = 0; i < es_header.length; i++) {
                JRDesignElement e = (JRDesignElement) es_header[i];
                String v = "";
                if (e instanceof JRStaticText) {
                    JRStaticText text = (JRStaticText) e;
                    v = text.getText();
                }
                if (!dynamiccolumns.contains(v)) {
                    for (int j = 0; j < es_detail.length; j++) {
                        JRDesignElement ee = (JRDesignElement) es_detail[i];
                        if (ee.getY() == e.getY()) {
                            cDetail.removeElement(ee);
                        }
                    }
                    cHeader.removeElement(e);
                }
            }
        }
        return jdesign;
    }

}
