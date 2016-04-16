package org.guili.ecshop.dao.hunsha;
import org.guili.ecshop.bean.hunsha.HunshaCompany;

/**
 * 婚纱公司dao
 * @author guili
 */
public interface IHunshaCompanyDao {
	
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
