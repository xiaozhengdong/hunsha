package org.guili.ecshop.bean.hunsha;

/**
 * 用于分页查询的参数
 * @author guili
 */
public class HunShaPageParam {
	//筛选条件
	private Integer TypeId;//地址id
	private Integer companyId;//公司id
	//分页查询
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getTypeId() {
		return TypeId;
	}
	public void setTypeId(Integer typeId) {
		TypeId = typeId;
	}
	
	
}
