package org.guili.ecshop.bean.hunsha;

/**
 * 用于分页查询的参数
 * @author guili
 */
public class HunShaPageParam {
	//筛选条件
	private Integer adressId;//地址id
	private Integer companyId;//公司id
	//分页查询
//	private int start;
//	private int size;
	public Integer getAdressId() {
		return adressId;
	}
	public void setAdressId(Integer adressId) {
		this.adressId = adressId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	
}
