package org.guili.ecshop.bean.hunsha;

import java.util.List;

/**
 * 婚纱照更新对象
 * @author zhengdong.xiao
 *
 */
public class HunshaZhaoTagVO {

	private List<HunshaZhaoTag> HunshaZhaoTags;
	private Integer hunshaId;
	private String keywords;
	
	public Integer getHunshaId() {
		return hunshaId;
	}
	public void setHunshaId(Integer hunshaId) {
		this.hunshaId = hunshaId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<HunshaZhaoTag> getHunshaZhaoTags() {
		return HunshaZhaoTags;
	}
	public void setHunshaZhaoTags(List<HunshaZhaoTag> hunshaZhaoTags) {
		HunshaZhaoTags = hunshaZhaoTags;
	}
	
	
}
