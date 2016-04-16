package org.guili.ecshop.business.impl.product;

import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunshaCompany;
import org.guili.ecshop.business.product.IHunshaCompanyService;
import org.guili.ecshop.dao.hunsha.IHunshaCompanyDao;

/**
 * 婚纱公司服务类
 * @author zhengdong.xiao
 *
 */
public class HunshaCompanyService implements IHunshaCompanyService {
	
	private static Logger logger=Logger.getLogger(HunshaCompanyService.class);
	private IHunshaCompanyDao  hunshaCompanyDao;
	@Override
	public HunshaCompany selectHunshaCompany(Integer id) throws Exception {
		return hunshaCompanyDao.selectHunshaCompany(id);
	}

	@Override
	public Integer saveHunshaCompany(HunshaCompany hunshaCompany)
			throws Exception {
		return hunshaCompanyDao.saveHunshaCompany(hunshaCompany);
	}

	public IHunshaCompanyDao getHunshaCompanyDao() {
		return hunshaCompanyDao;
	}

	public void setHunshaCompanyDao(IHunshaCompanyDao hunshaCompanyDao) {
		this.hunshaCompanyDao = hunshaCompanyDao;
	}
	
}
