package org.guili.ecshop.bean.hunsha;

//婚纱公司列表
public class HunshaCompany {
 	private Integer id;
 	private String  companyName;//公司名
 	private String companyAddress;//公司地址
 	private Integer addressId;//公司地址所在市
 	private Integer  orderId;//顺序
 	private int   status;//状态 0可用 1，不可用
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
}
