package org.guili.ecshop.business.impl.product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunshaCompany;
import org.guili.ecshop.bean.hunsha.HunshaCompanyBean;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.bean.hunsha.HunshaZhaoBean;
import org.guili.ecshop.exception.RemoteServiceException;
import org.guili.ecshop.util.AbstractResponseHandler;
import org.guili.ecshop.util.FileTools;
import org.guili.ecshop.util.HttpComponent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * 婚纱-点评抓取
 * @author zhengdong.xiao
 *
 */
public class HunShaDPSpider extends HunShaSpiderParent {

	private static Logger logger=Logger.getLogger(HunShaDPSpider.class);
	private static final String USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";
	//http请求组件封装
	private HttpComponent httpComponent;
	
	/**
	 * 查询公司列表
	 */
	@Override
	public List<HunshaCompanyBean> getCompanyListInfo(String listurl)
			throws URISyntaxException, IOException {
		HttpGet httpGet = new HttpGet(listurl);
		httpGet=this.setHeader(httpGet);
		httpComponent=this.buildHttpComponent();
		return httpComponent.execute(httpGet, hunShaListHandler);
	}

	/**
	 * 查询婚纱集列表
	 */
	@Override
	public List<HunshaZhaoBean> getHunshaZhaos(String oneCompanyUrl)
			throws URISyntaxException, IOException {
		//获取照片集信息
		String listImageUrl="http://www.dianping.com/wed/ajax/shopweb/case_cases";
		HttpPost httpPost=new HttpPost(listImageUrl);
		//设置参数
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();  
        params.add(new BasicNameValuePair("fallPage", "1"));  
        params.add(new BasicNameValuePair("productCategoryId", "0"));  
        params.add(new BasicNameValuePair("tagValues", "0"));  
        params.add(new BasicNameValuePair("shopId", "8975141"));  
        params.add(new BasicNameValuePair("page", "1"));  
     // Post请求  
        // 设置参数  
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8")); 
        //设置组件
        httpComponent=this.buildHttpComponent();
		List<HunshaZhaoBean> hunshaZhaoBeans=httpComponent.execute(httpPost, hunShaInfoDetailHandler);
		return hunshaZhaoBeans;
	}
	
	/**
	 * 查询单个婚纱集图片
	 */
	@Override
	public HunshaZhao getHunshaZhao(String detailUrl)
			throws URISyntaxException, IOException {
		HttpGet httpGet = new HttpGet(detailUrl);
		httpGet=this.setHeader(httpGet);
		httpComponent=this.buildHttpComponent();
		HunshaZhao hunshaZhao=httpComponent.execute(httpGet, hunShaBigImgHandler);
		return hunshaZhao;
	}
	
	
	
	//婚纱列表handler
	private final HunShaListHandler hunShaListHandler = new HunShaListHandler();
	//婚纱图片详细handler
	private final HunShaInfoDetailHandler hunShaInfoDetailHandler = new HunShaInfoDetailHandler();
	//婚纱大图
	private final HunShaBigImgHandler hunShaBigImgHandler = new HunShaBigImgHandler();
	
	//婚纱列表查询
	private class HunShaListHandler extends AbstractResponseHandler<List<HunshaCompanyBean>> {
        @Override
        public List<HunshaCompanyBean> handle(HttpEntity entity) throws IOException {
            String entityStr = EntityUtils.toString(entity);

            if (StringUtils.isBlank(entityStr)) {
                throw new RemoteServiceException();
            }
            if(entityStr==null || entityStr.length()<1384){
            	logger.error("connect Exception----entityStr.length() is so short");
            }
//            FileTools.write("E:/spiderhunsha/dianpinglist2.txt", entityStr);
            return convertContent(entityStr);
        }
    }
	
	/**
	 * 找公司
	 * @param pagexml
	 */
	private List<HunshaCompanyBean>  convertContent(String pagexml){
		if(pagexml==null){
			return null;
		}
		//从文件加载
//		try {
//			pagexml=FileTools.read("E:/spiderhunsha/dianpinglist1.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Document doc = Jsoup.parse(pagexml);
        Elements shops =doc.select(".shop-list li");
        List<HunshaCompanyBean> hunshaCompanyBeans=Lists.newArrayList();
        for(Element shop:shops){
        	Elements shopas=shop.select("a");
        	String title=shopas.get(0).attr("title");
        	String oneCompanyUrl=shopas.get(0).attr("href");
        	String address=shop.select(".area-list").html();
        	System.out.println("title:"+title);
        	System.out.println("oneCompanyUrl:"+oneCompanyUrl);
        	System.out.println("address:"+address);
        	System.out.println("-------------------------------------------");
        	
        	HunshaCompanyBean hunshaCompanyBean=new HunshaCompanyBean();
        	HunshaCompany hunshaCompany=new HunshaCompany();
        	hunshaCompanyBean.setOneCompanyUrl(oneCompanyUrl);
        	hunshaCompany.setCompanyName(title);
        	hunshaCompany.setCompanyAddress(address);
        	hunshaCompanyBean.setHunshaCompany(hunshaCompany);
        	hunshaCompanyBeans.add(hunshaCompanyBean);
        }
        return hunshaCompanyBeans;
	}
	
	//hunsha detail
	private class HunShaInfoDetailHandler extends AbstractResponseHandler<List<HunshaZhaoBean>> {
        @Override
        public List<HunshaZhaoBean> handle(HttpEntity entity) throws IOException {
            String entityStr = EntityUtils.toString(entity);

            if (StringUtils.isBlank(entityStr)) {
                throw new RemoteServiceException();
            }
//            FileTools.write("E:/spiderhunsha/hunshatestdetail1.txt", entityStr);
            if(entityStr==null || entityStr.length()<20384){
            	logger.error("connect Exception----entityStr.length() is so short");
            }
            //解析并返回list
            return  convertPostContent(entityStr);
            
        }
    }
	
	private List<HunshaZhaoBean> convertPostContent(String pagexml){
		if(pagexml==null){
			return null;
		}
		//从文件加载
//		try {
//			pagexml=FileTools.read("E:/spiderhunsha/hunshatestdetail1.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		JSONObject jsonString=JSON.parseObject(pagexml);
		
		String html=jsonString.getJSONObject("msg").getString("html").replaceAll("\n", "");
//		 FileTools.write("E:/spiderhunsha/dianpinglist_success.txt", html);
		Document doc = Jsoup.parse(html);
        Elements shops =doc.select("li");
        
        List<HunshaZhaoBean> hunshaZhaoBeans=Lists.newArrayList();
        if(shops.isEmpty()){
        	return null;
        }
        for(Element shop:shops){
        	Elements shopas=shop.select("a");
        	String title=shopas.get(0).attr("title");
        	String detailUrl=shopas.get(0).attr("href");
        	String headTupian=shopas.select("img").attr("src");
        	System.out.println("title:"+title);
        	System.out.println("href:"+detailUrl);
        	System.out.println("image:"+headTupian);
        	System.out.println("-------------------------------------------");
        	
        	//构造返回对象
        	HunshaZhaoBean hunshaZhaoBean=new HunshaZhaoBean(); 
        	HunshaZhao hunshaZhao=new HunshaZhao();
        	hunshaZhaoBean.setDetailUrl(detailUrl);
        	hunshaZhao.setGroupName(title);
        	hunshaZhao.setHeadTupian(headTupian);
        	hunshaZhao.setKeywords(title);
        	hunshaZhaoBean.setHunshaZhao(hunshaZhao);
        	//构造list
        	hunshaZhaoBeans.add(hunshaZhaoBean);
        }
        return hunshaZhaoBeans;
	}
	//婚纱大图列表
	private class HunShaBigImgHandler extends AbstractResponseHandler<HunshaZhao> {
        @Override
        public HunshaZhao handle(HttpEntity entity) throws IOException {
            String entityStr = EntityUtils.toString(entity);

            if (StringUtils.isBlank(entityStr)) {
                throw new RemoteServiceException();
            }
//            FileTools.write("E:/spiderhunsha/hunshabigImg_test.txt", entityStr);
            if(entityStr==null || entityStr.length()<1384){
            	logger.error("connect Exception----entityStr.length() is so short");
            }
            //解析
            return convertBigImg(entityStr);
        }
    }
	
	private  HunshaZhao convertBigImg(String pagexml){
		if(pagexml==null){
			return null;
		}
		//从文件加载
//		try {
//			pagexml=FileTools.read("E:/spiderhunsha/hunshabigImg_test.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		int startindex=pagexml.indexOf("picBig:[")+"picBig:[".length();
		int endindex=pagexml.indexOf("]", startindex);
		if(startindex>=endindex){
			
		}
		String imgString=pagexml.substring(startindex, endindex);
		System.out.println(imgString);
		
		String [] imgUrls=imgString.split(",");
		String imgs="";
		for(String imgurl:imgUrls){
			imgs=imgurl.replaceAll("\"", "");
			System.out.println("img:"+imgs);
		}
		HunshaZhao hunshaZhao=new HunshaZhao();
		hunshaZhao.setTupians(imgs);
		return hunshaZhao;
	}
	
	/**
	 * 设置header
	 * @param httpGet
	 * @return
	 */
	private HttpGet setHeader(HttpGet httpGet){
		httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
		httpGet.addHeader("REFERER", "http://www.dianping.com");
		httpGet.addHeader("Cookie", "JSESSIONID=872AA6A2890DC5B71D358608AD93C9B3; PHOENIX_ID=1539bab6104; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1458605617;");
		//设置user-Agent
		httpGet.addHeader("User-Agent", USER_AGENT);
		return httpGet;
	}
	
	/**
	 * 设置代理
	 * @param httpComponent
	 * @return
	 */
	private HttpComponent buildHttpComponent(){
		httpComponent=new HttpComponent();
//		httpComponent.setProxy("27.115.75.114",8080 );
		return httpComponent;
	}

	public HttpComponent getHttpComponent() {
		return httpComponent;
	}

	public void setHttpComponent(HttpComponent httpComponent) {
		this.httpComponent = httpComponent;
	}

	@Override
	public HunShaSpiderType[] getHunShaSpiderType() {
		 return new HunShaSpiderType[] {HunShaSpiderType.SHANGHAI};
	}

}
