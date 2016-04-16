package org.guili.ecshop.business.product;

import org.guili.ecshop.bean.hunsha.HunshaCompany;

/**
 * 婚纱服务接口
 * @ClassName:   IHunshaService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2014-1-13 下午4:11:27 
 */
public interface IHunshaCompanyService {
	
	
	/**
	 *查询婚纱公司
	 * @return
	 */
	public HunshaCompany selectHunshaCompany(Integer id) throws Exception;
	
	/**
	 *保存婚纱公司
	 * @return
	 */
	public Integer saveHunshaCompany(HunshaCompany hunshaCompany) throws Exception;
	
}
