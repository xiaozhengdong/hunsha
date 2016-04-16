package org.guili.ecshop.impl.hunsha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunshaCompany;
import org.guili.ecshop.dao.hunsha.IHunshaCompanyDao;
import org.guili.ecshop.util.BasicSqlSupport;

/**
 * 婚纱公司dao
 * @author Administrator
 *
 */
public class HunshaCompanyDao extends BasicSqlSupport implements IHunshaCompanyDao {
	private static Logger logger=Logger.getLogger(HunshaCompanyDao.class);

	@Override
	public HunshaCompany selectHunshaCompany(Integer id) throws Exception {
		HunshaCompany hunshaCompany=new HunshaCompany();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaCompanyDao.selectHunshaCompany", paramMap);
		if(list!=null && list.size()>0){
			hunshaCompany=(HunshaCompany)list.get(0);
		}else{
			hunshaCompany=null;
		}
		logger.debug("success!");
		return hunshaCompany;
	}

	@Override
	public Integer saveHunshaCompany(HunshaCompany hunshaCompany)
			throws Exception {
		int count=0;
		count=this.session.insert("org.guili.ecshop.dao.hunsha.IHunshaCompanyDao.saveHunshaCompany", hunshaCompany);
		return count;
	}
	
	
}
