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
import org.guili.ecshop.bean.hunsha.HunShaSpiderCompanyEnum;
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
 * 米兰婚纱
 * @author zhengdong.xiao
 *
 */
public class ArtizHunShaSpider extends HunShaSpiderParent {

	  private static Logger logger = Logger.getLogger(ArtizHunShaSpider.class);
	  private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";
	  private static final int MAX_IMG_SIZE = 12;
	  private static final String COMPANY_URL = "http://www.artiz.com.cn";	//http请求组件封装
	private HttpComponent httpComponent;
	
	 public List<HunshaCompanyBean> getCompanyListInfo(String listurl)
			    throws URISyntaxException, IOException
			  {
			    HunshaCompanyBean hunshaCompanyBean = HunShaSpiderCompanyEnum.getHunshaCompanyBean(HunShaSpiderCompanyEnum.Artiz);
			    hunshaCompanyBean.setOneCompanyUrl(listurl);
			    List<HunshaCompanyBean> hunshaCompanyBeanList = Lists.newArrayList();
			    hunshaCompanyBeanList.add(hunshaCompanyBean);
			    return hunshaCompanyBeanList;
			  }
			  

	 public List<HunshaZhaoBean> getHunshaZhaos(String onePhotoUrl)
			    throws URISyntaxException, IOException
			  {
			    this.httpComponent = new HttpComponent();
			    HttpGet httpGet = new HttpGet(onePhotoUrl);
			    httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
			    httpGet.addHeader("REFERER", "http://www.dianping.com");
			    httpGet.addHeader("Cookie", "JSESSIONID=872AA6A2890DC5B71D358608AD93C9B3; PHOENIX_ID=1539bab6104; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1458605617;");
			    
			    httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			    
			    this.httpComponent = buildHttpComponent();
			    List<HunshaZhaoBean> hunshaZhaoBeans = (List)this.httpComponent.execute(httpGet, this.hunShaInfoDetailHandler);
			    return hunshaZhaoBeans;
			  }
	
	 public HunshaZhao getHunshaZhao(String detailUrl)
			    throws URISyntaxException, IOException
			  {
			    HttpGet httpGet = new HttpGet(detailUrl);
			    httpGet = setHeader(httpGet);
			    this.httpComponent = buildHttpComponent();
			    HunshaZhao hunshaZhao = (HunshaZhao)this.httpComponent.execute(httpGet, this.hunShaBigImgHandler);
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
	
	private List<HunshaCompanyBean> convertContent(String pagexml)
	  {
	    if (pagexml == null) {
	      return null;
	    }
	    Document doc = Jsoup.parse(pagexml);
	    Elements shops = doc.select(".shop-list li");
	    List<HunshaCompanyBean> hunshaCompanyBeans = Lists.newArrayList();
	    for (Element shop : shops)
	    {
	      Elements shopas = shop.select("a");
	      String title = shopas.get(0).attr("title");
	      String oneCompanyUrl = shopas.get(0).attr("href");
	      String address = shop.select(".area-list").html();
	      System.out.println("title:" + title);
	      System.out.println("oneCompanyUrl:" + oneCompanyUrl);
	      System.out.println("address:" + address);
	      System.out.println("-------------------------------------------");
	      
	      HunshaCompanyBean hunshaCompanyBean = new HunshaCompanyBean();
	      HunshaCompany hunshaCompany = new HunshaCompany();
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
	
	private List<HunshaZhaoBean> convertPostContent(String pagexml)
	  {
	    if (pagexml == null) {
	      return null;
	    }
	    Document doc = Jsoup.parse(pagexml);
	    Elements shops = doc.select(".block_product");
	    
	    List<HunshaZhaoBean> hunshaZhaoBeans = Lists.newArrayList();
	    if (shops.isEmpty()) {
	      return null;
	    }
	    for (Element shop : shops)
	    {
	      Elements shopas = shop.select("a");
	      Element onePhoto = shopas.get(0);
	      String title = onePhoto.attr("title");
	      String detailUrl = "http://www.artiz.com.cn/" + onePhoto.attr("href");
	      String headTupian = "http://www.artiz.com.cn" + onePhoto.select("img").attr("data-original");
	      
	      HunshaZhaoBean hunshaZhaoBean = new HunshaZhaoBean();
	      HunshaZhao hunshaZhao = new HunshaZhao();
	      hunshaZhaoBean.setDetailUrl(detailUrl);
	      hunshaZhao.setGroupName(title);
	      hunshaZhao.setHeadTupian(headTupian);
	      hunshaZhao.setKeywords(title);
	      hunshaZhaoBean.setHunshaZhao(hunshaZhao);
	      
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
	

	  private HunshaZhao convertBigImg(String pagexml)
	  {
	    if (pagexml == null) {
	      return null;
	    }
	    Document doc = Jsoup.parse(pagexml);
	    Elements shops = doc.select(".contentmain").select("img");
	    StringBuffer imgs = new StringBuffer();
	    for (int i = 0; i < shops.size(); i++)
	    {
	      if (i > 12) {
	        break;
	      }
	      Element shop = shops.get(i);
	      String img = shop.attr("src");
	      imgs.append("http://www.artiz.com.cn" + img + ",");
	      System.out.println("img:" + shop.attr("src"));
	      System.out.println("-------------------------------------------");
	    }
	    HunshaZhao hunshaZhao = new HunshaZhao();
	    if (imgs.length() > 0) {
	      hunshaZhao.setTupians(imgs.substring(0, imgs.length() - 1));
	    }
	    return hunshaZhao;
	  }
	
	  
	  private HttpGet setHeader(HttpGet httpGet)
	  {
	    httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
	    httpGet.addHeader("REFERER", "http://www.dianping.com");
	    httpGet.addHeader("Cookie", "JSESSIONID=872AA6A2890DC5B71D358608AD93C9B3; PHOENIX_ID=1539bab6104; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1458605617;");
	    
	    httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
	    return httpGet;
	  }
	  
	  private HttpComponent buildHttpComponent()
	  {
	    this.httpComponent = new HttpComponent();
	    
	    return this.httpComponent;
	  }
	  
	  public HttpComponent getHttpComponent()
	  {
	    return this.httpComponent;
	  }
	  
	  public void setHttpComponent(HttpComponent httpComponent)
	  {
	    this.httpComponent = httpComponent;
	  }
	  
	  public HunShaSpiderType[] getHunShaSpiderType()
	  {
	    return new HunShaSpiderType[] { HunShaSpiderType.SHANGHAI };
	  }
	  
	  

	  public static void main(String[] args)
	    throws InterruptedException
	  {
	    ArtizHunShaSpider ganjiPetSpider = new ArtizHunShaSpider();
	    String urlDetail = "http://www.artiz.com.cn/content_1.aspx";
	    String hunshaBigimg = "http://www.dianping.com/shop/8975141/wedding/cases/1490";
	    try
	    {
	      ganjiPetSpider.convertBigImg("");
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }

}
