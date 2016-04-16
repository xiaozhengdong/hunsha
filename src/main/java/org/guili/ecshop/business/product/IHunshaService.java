package org.guili.ecshop.business.product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.guili.ecshop.bean.hunsha.HunshaCompany;
import org.guili.ecshop.bean.hunsha.HunshaCompanyBean;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.bean.hunsha.HunshaZhaoBean;
import org.guili.ecshop.business.impl.product.HunShaSpiderType;

/**
 * 婚纱服务接口
 * @ClassName:   IHunshaService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2014-1-13 下午4:11:27 
 */
public interface IHunshaService {
	
	
	public HunShaSpiderType[] getHunShaSpiderType();
	
	/**
	 * 抓取宠物信息列表
	 * @param listurl 宠物信息列表url
	 * 	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public List<HunshaCompanyBean> getCompanyListInfo(String listurl) throws URISyntaxException, IOException;
	
	/**
	 * 获取单个宠物信息
	 * @param detailturl
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public List<HunshaZhaoBean> getHunshaZhaos(String oneCompanyUrl)throws URISyntaxException, IOException;
	
	/**
	 * 抓取并保持信息
	 * @param listurl
	 */
	public void batCrawlHunsha(String listurl);
	
	
	/**
	 * 根据url获取单个宠物信息
	 * @param detailturl
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public HunshaZhao getHunshaZhao(String detailUrl)throws URISyntaxException, IOException;
	
}
