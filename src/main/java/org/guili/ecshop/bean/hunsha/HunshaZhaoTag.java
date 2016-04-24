package org.guili.ecshop.bean.hunsha;

//婚纱照标签
public class HunshaZhaoTag {
 	private Integer id;
 	private Integer hunsha_id;//顺序
 	private Integer tag_id;//公司地址所在市
 	private String  tag_name;	//关键字
 	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHunsha_id() {
		return hunsha_id;
	}
	public void setHunsha_id(Integer hunsha_id) {
		this.hunsha_id = hunsha_id;
	}
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
}
