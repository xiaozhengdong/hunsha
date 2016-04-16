package org.guili.ecshop.business.impl.product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunshaCompany;
import org.guili.ecshop.bean.hunsha.HunshaCompanyBean;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.bean.hunsha.HunshaZhaoBean;
import org.guili.ecshop.business.product.IHunshaCompanyService;
import org.guili.ecshop.business.product.IHunshaService;
import org.guili.ecshop.business.product.IHunshaZhaoService;

/**
 * 婚纱抓取父类别
 * @author zhengdong.xiao
 *
 */
public abstract class HunShaSpiderParent implements IHunshaService{
	
	private static Logger logger=Logger.getLogger(HunShaSpiderParent.class);
	
	private IHunshaZhaoService hunshaZhaoService;
	private IHunshaCompanyService hunshaCompanyService;
//	private static final String HEAD_URL="http://www.dianping.com";
	//批量抓取婚纱
	@Override
	public void batCrawlHunsha(String listurl) {
		
		//1.抓取婚纱公司信息。
		try {
			//公司列表
			List<HunshaCompanyBean> hunshaCompanyBeanList=this.getCompanyListInfo(listurl);
			//2.抓取单个公司多个婚纱集
			if(hunshaCompanyBeanList.isEmpty()){
				return;
			}
			
//			for(HunshaCompanyBean hunshaCompanyBean:hunshaCompanyBeanList){
			for(int i=0;i<hunshaCompanyBeanList.size();i++){
				
				HunshaCompanyBean hunshaCompanyBean=hunshaCompanyBeanList.get(i);
				//查询单个公司多个婚纱照集锦
				List<HunshaZhaoBean> hunshaZhaoList=this.getHunshaZhaos(hunshaCompanyBean.getOneCompanyUrl());
				//查询当前集锦的图片列表
				if(hunshaZhaoList.isEmpty()){
					continue;
				}
				
				//保存婚纱公司
				HunshaCompany hunshaCompany=hunshaCompanyBean.getHunshaCompany();
				//设置市
				hunshaCompany.setAddressId(this.getHunShaSpiderType()[0].getIndex());
				Integer companyId=0;
				try {
					companyId = hunshaCompanyService.saveHunshaCompany(hunshaCompany);
				} catch (Exception e) {
					logger.error("save HunshaCompany "+hunshaCompany.getCompanyName()+" error!");
					continue;
				}
				
				//循环抓取照片集
				for(HunshaZhaoBean hunshaZhaoBean:hunshaZhaoList){
					//3.抓取单个婚纱照集，内容。
					HunshaZhao hunshaZhao =hunshaZhaoBean.getHunshaZhao();
					//抓取图片内容
					HunshaZhao hunshaZhaoTemp=this.getHunshaZhao(hunshaZhaoBean.getDetailUrl());
					hunshaZhao.setTupians(hunshaZhaoTemp.getTupians());
					//设置市
					hunshaZhao.setAddressId(hunshaCompany.getAddressId());
					hunshaZhao.setCompanyName(hunshaCompany.getCompanyName());
					hunshaZhao.setCompanyId(companyId);
					//保存婚纱照
					try {
						hunshaZhaoService.saveHunshaZhao(hunshaZhao);
					} catch (Exception e) {
						logger.error("save HunshaCompany "+hunshaZhao.getGroupName()+" error!");
						continue;
					}
				}
				if(i>0){
					break;
				}
				
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public IHunshaZhaoService getHunshaZhaoService() {
		return hunshaZhaoService;
	}
	public void setHunshaZhaoService(IHunshaZhaoService hunshaZhaoService) {
		this.hunshaZhaoService = hunshaZhaoService;
	}
	public IHunshaCompanyService getHunshaCompanyService() {
		return hunshaCompanyService;
	}
	public void setHunshaCompanyService(IHunshaCompanyService hunshaCompanyService) {
		this.hunshaCompanyService = hunshaCompanyService;
	}
	
}
