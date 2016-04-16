package org.guili.ecshop.business.impl.product;

/**
 * 爬虫地区类型
 * @author Administrator
 *
 */
public enum HunShaSpiderType {
	SHANGHAI("上海", 1,1), 
	BEIJING("北京", 2,2), 
	GUANGZHOU("广州", 3,3),
	SHENZHEN("深圳", 4,4),
	HANGZHOU("杭州", 5,5),
	SUZHOU("苏州", 6,6),
	;
    // 成员变量
    private String name;
    private int index;
    private int companyId;

    // 构造方法
    private HunShaSpiderType(String name, int index,int companyId) {
        this.name = name;
        this.index = index;
        this.companyId=companyId;
    }

    // 普通方法
    public static String getName(int index) {
        for (HunShaSpiderType c : HunShaSpiderType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
}
