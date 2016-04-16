package org.guili.ecshop.bean.hunsha;

import org.guili.ecshop.business.impl.product.HunShaSpiderType;

/**
 * 爬虫地区类型
 * @author Administrator
 *
 */
public enum HunShaSpiderCompanyEnum {
	MILAN("米兰婚纱", HunShaSpiderType.SHANGHAI.name(),HunShaSpiderType.SHANGHAI.getIndex(),HunShaSpiderType.SHANGHAI.getCompanyId(),0),
	Artiz("韩国艺匠", HunShaSpiderType.SHANGHAI.name(),HunShaSpiderType.SHANGHAI.getIndex(),HunShaSpiderType.SHANGHAI.getCompanyId(),0),
	;
//	`companyName` varchar(200) NOT NULL DEFAULT '' COMMENT '公司名称',
//	  `companyAddress` varchar(300) NOT NULL DEFAULT '0' COMMENT '公司地址',
//	  `addressId` int(10) DEFAULT '1' COMMENT '婚纱公司所在市',
//	  `orderId` int(10) DEFAULT '0',
//	  `status` int(5) DEFAULT '1',
    // 成员变量
    private String companyName;//公司名
    private String companyAddress;//公司地址
    private int addressId;//公司地址id
    private int companyId;//公司id
    private int status;

    // 构造方法
    private HunShaSpiderCompanyEnum(String companyName, String companyAddress,int addressId,int companyId,int status) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.addressId = addressId;
        this.companyId=companyId;
        this.status = status;
    }
    
    public static HunshaCompanyBean getHunshaCompanyBean(HunShaSpiderCompanyEnum companyEnum){
    	HunshaCompanyBean hunshaCompanyBean=new HunshaCompanyBean();
    	HunshaCompany hunshaCompany=new HunshaCompany();
    	hunshaCompany.setAddressId(companyEnum.addressId);
    	hunshaCompany.setCompanyAddress(companyEnum.companyAddress);
    	hunshaCompany.setCompanyName(companyEnum.companyName);
//    	hunshaCompany.setId(companyEnum.getCompanyId());
    	hunshaCompanyBean.setHunshaCompany(hunshaCompany);
    	return hunshaCompanyBean;
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
