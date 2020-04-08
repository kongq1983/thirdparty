package com.kq.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * TODO
 * @author kongqi
 * @date  2018-01-23 19:07 
 * @since 
 */
public class JSOUPUtil {
	
//	protected static Logger logger = LoggerFactory.getLogger(JSOUPUtil.class);

	public static final String IE = "ie";
	public static final String CHROME = "chrome";


	public static void dealPhoto(InputStream in,OutputStream out) throws IOException {
		dealPhoto(in,out,CHROME);
	}


	public static void dealPhoto(InputStream in,OutputStream out,String browserType) throws IOException {

//		logger.info("dealPhoto browserType={}",browserType);

//		InputStream in = new FileInputStream("C:\\Users\\kongqi\\eclipse-workspace\\com.zyxk.sl.wsgwservice\\b.html");
		Document rootDoc = Jsoup.parse(in, "UTF-8", "");
		
		Elements tds = rootDoc.select("div>div:last-child");
//		System.out.println("td.length="+tds.size());
		for(Element td : tds) {
//			System.out.println(td.attr("style"));
			int index = td.attr("style").indexOf("background");
//			System.out.println("index="+index);
			if(index>0) {
//				System.out.println(td.select("div[style]"));
				String style = td.attr("style");
				
				System.out.println(style);
				
				Element parent = td.parent().parent();
				
				String html = "<img src=\""+getUrl(style)+"\" style=\"width: 120px\" alt=\"\"/>";
				
				td.parent().remove();
				
				parent.append(html);

//				Element ele = td.wrap(style);
//				System.out.println("ele:"+ele);
//				System.out.println(ele.attr("background-image"));
			}
		}

		//处理table
		dealTable(rootDoc);
		dealBreakWord(rootDoc,"备注：");
		//处理换行问题  8个菜以上，会有很大的空行
		dealLongEmptyTr(rootDoc);

		if(StringUtils.equals(IE,browserType)) {
			dealForIE(rootDoc);
		}

//		logger.info("网购小票报表内容:{}",rootDoc.toString());
		
		IOUtils.write(rootDoc.toString().getBytes("UTF-8"), out);
		
		
	}
	
	
//	<td width="50%">&nbsp;</td>
//	<td align="center"> <a name="JR_PAGE_ANCHOR_0_1"></a> 
//	<table class="jrPage" cellpadding="0" cellspacing="0" border="0" style="empty-cells: show; width: 596px; border-collapse: collapse; background-color: white;"> 
	
	public static void dealTable(Document rootDoc)  throws IOException {
		Elements tds = rootDoc.select("table tr>td[width^=50]:first-child");
		//设置align=left
		
		if(tds!=null) {
			Elements nextElement = tds.next();
			if(nextElement!=null) {
				nextElement.attr("align","left");
				System.out.println("nextElement:"+nextElement.toString());
			}
			//删除
			tds.remove();
			System.out.println("tds:"+tds.toString());
		}
		
		
		Elements jrPageLastTr = rootDoc.select("table[class=jrPage] tr");
		if(jrPageLastTr!=null) {
			Element lastE = jrPageLastTr.last();
			if(lastE!=null) {
				lastE.attr("style","height:66px");
			}
			
		}
		System.out.println("jrPageLastTr="+jrPageLastTr);
		
	}

	/**
	 * 换行
	 * @param rootDoc
	 * @param key
	 */
	public static void dealBreakWord(Document rootDoc,String key) {
//		logger.info("调用处理模板数据 dealBreakWord");
		key = StringUtils.trimToNull(key);
		if(key==null) return;
		Elements tds = rootDoc.select("td>span:matches("+key+")");

		if(tds!=null) {
//			logger.info("search element key={},size={}",tds.size());
			for (int i = 0; i < tds.size(); i++) {
				Element e = (Element)tds.get(i);
				if(StringUtils.trimToEmpty(e.text()).equals(key)) {
					System.out.println("text=" + e);
					//tr
					Element trElement = e.parent().parent();

					Elements tdEles = trElement.select("td");

					Element dealTdEle = null;
					if(tdEles!=null) {
						for (int j = 0; j < tdEles.size(); j++) {
							Element tdEle = (Element)tdEles.get(j);
							String style = StringUtils.trimToEmpty(tdEle.attr("style"));
//							logger.info("style={}",style);
							if(StringUtils.isNotBlank(style)) {
								dealTdEle = tdEle;
							}
						}
					}

//					logger.info("处理key={},Element={}",key,dealTdEle);

					//取最后一个不为空的样式的td
					if(dealTdEle!=null) {
						String style = StringUtils.trimToEmpty(dealTdEle.attr("style"));
						style = style + "word-wrap:break-word;word-break:break-all;width:128px;";
						dealTdEle.attr("style", style);
					}

				}

			}
		}else {
//			logger.info("search element key={},size=0",key);
		}

//		if(tds!=null) {
//			Elements nextElement = tds.next();
//			while(nextElement!=null) {
//
//				String value = tds.val();
//				System.out.println("value="+value);
//				nextElement = tds.next();
//			}
//
//
//		}

	}


	/**
	 * 处理换行问题  8个菜以上，会有很大的空行
	 * 并删除br
	 * 处理大于height >50 的 空tr
	 * @param rootDoc
	 */
	public static void dealLongEmptyTr(Document rootDoc) {

//		logger.info("dealLongEmptyTr============================");

		if(rootDoc==null) return;

		Elements trs = rootDoc.select("table.jrPage > tbody > tr:last-child"); // tr:last-child

//		System.out.println("length="+rootDoc.select("table.jrPage > tbody > tr:last-child").size());
//		System.out.println("length="+rootDoc.select("table.jrPage > tbody > tr:last-child").size());
//		logger.info("length="+rootDoc.select("table.jrPage > tbody > tr:last-child"));
//		System.out.println("length="+rootDoc.select("table.jrPage:first-child").size());

		if(trs!=null) {
//			logger.info("dealLongEmptyTr:trs:"+trs.toString());

			for (int j = 0; j < trs.size(); j++) {
				Element trEle = (Element) trs.get(j);
				if(trEle!=null) {
					String style = trEle.attr("style");

					String[] attrs = StringUtils.split(style,";");

					for(String attr : attrs) {
						if(StringUtils.startsWith(attr,"height:")) {
							String[] kv =  StringUtils.split(attr,":");

							String value = kv[1];
							//>100 删除
							if(NumberUtils.toInt(StringUtils.replace(value,"px",""),0)>100) {

								Elements tds = trEle.select("td");
								boolean remove = true;
								if(tds!=null) {
									for(int i=0;i<tds.size();i++) {
										Element tdE = tds.get(i);
										if(tdE!=null){
											String tdText = tdE.text();
//											logger.info("判断删除TD的text={}",tdText);
											if(StringUtils.isNotBlank(tdText)) {
												remove = false;
												break;
											}
										}
									}
								}

								if(remove) {
//									logger.info("删除TR信息:{}", trEle);
									trEle.remove();
								}
							}

						}
					}


//					logger.info("dealLongEmptyTr style={}",style);
				}

			}

		}

		//删除br
		Elements tr1s = rootDoc.select("table.jrPage");
//		logger.info("jrPage table {}",tr1s);


		if(tr1s!=null) {
			for (int i = 0; i < tr1s.size(); i++) {
				Elements brElement = tr1s.parents().select("br");
				if(brElement!=null) {
//					logger.info("remove br tag ={}",brElement);
					brElement.remove();
				}
			}
		}


	}


	/**
	 * chrome浏览器不要调用这个
	 * 处理ie
	 * @param rootDoc
	 */
	public static void dealForIE(Document rootDoc) {
//		logger.info("调用处理兼容IE代码============================");
		if(rootDoc==null) return;


		Elements trs = rootDoc.select("table.jrPage"); // tr:last-child

		if(trs!=null) {
			//处理第一个
			Element jrPageEle = trs.get(0);
			if(jrPageEle!=null) {
				String style = jrPageEle.attr("style");
//				logger.info("table.jrPage[0] style={}",style);

				style = style+";"+"width: 395px;";

				jrPageEle.attr("style",style);

			}

//			logger.info("table.jrPage = {}",trs);
		}



	}



	private static String getUrl(String style) {
		int start = style.indexOf("url('");
		if(start>0) {
			style = style.substring(start+5);
			System.out.println(style);
			start = style.indexOf("');");
			if(start>0) {
				style = style.substring(0,start);
				System.out.println(style);
			}
		}
		return style;
	}


	public static void mainTr(String args[]) throws Exception{

		String[] types = {IE,CHROME};

		for(String type : types) {

			InputStream in = new FileInputStream("D:\\tmp\\print\\0802.html");
			Document rootDoc = Jsoup.parse(in, "UTF-8", "");
			dealBreakWord(rootDoc, "备注：");
			dealLongEmptyTr(rootDoc);
			if(type.equals(IE)) dealForIE(rootDoc);

			FileOutputStream out = new FileOutputStream("D:\\tmp\\print\\0802_deal_"+type+".html");
			IOUtils.write(rootDoc.toString().getBytes("UTF-8"), out);


		}
	}

	public static void main(String[] args) throws Exception {
//		InputStream in = new FileInputStream("C:\\Users\\kongqi\\eclipse-workspace\\com.zyxk.sl.wsgwservice\\b.html");
//		InputStream in = new FileInputStream("D:\\tmp\\bb.html");
		InputStream in = new FileInputStream("c:\\print\\xppage_1.html");
		Document rootDoc = Jsoup.parse(in, "UTF-8", "");

		dealTable(rootDoc);

		FileOutputStream out = new FileOutputStream("c:\\print\\xppage_1_deal.html");
		IOUtils.write(rootDoc.toString().getBytes("UTF-8"), out);
	}

}
