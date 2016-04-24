package org.guili.ecshop.bean.hunsha;

import java.util.List;

//婚纱照
public class HunshaZhao {
 	private Integer id;
 	private String  groupName;	//照片集合
 	private Integer addressId;//公司地址所在市
 	private String  companyName;//公司名
 	private String  keywords;	//关键字
 	private String  headTupian; //图片集显示图片
 	private String  tupians;	//图片集所有图片
 	private Integer companyId;//顺序
 	
 	private List<String> allTupians;
 	private List<HunshaZhaoTag> tags;
 	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getHeadTupian() {
		return headTupian;
	}
	public void setHeadTupian(String headTupian) {
		this.headTupian = headTupian;
	}
	public String getTupians() {
		return tupians;
	}
	public void setTupians(String tupians) {
		this.tupians = tupians;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public List<String> getAllTupians() {
		return allTupians;
	}
	public void setAllTupians(List<String> allTupians) {
		this.allTupians = allTupians;
	}
	public List<HunshaZhaoTag> getTags() {
		return tags;
	}
	public void setTags(List<HunshaZhaoTag> tags) {
		this.tags = tags;
	}
	
	
	
}
