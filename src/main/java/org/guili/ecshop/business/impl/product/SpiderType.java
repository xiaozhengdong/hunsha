package org.guili.ecshop.business.impl.product;

/**
 * 爬虫类型
 * @author Administrator
 *
 */
public enum SpiderType {
	WUBA("WUBA", 1), 
	HUNSHA("HUNSHA", 3), 
	GANJI("GANJI", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private SpiderType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (SpiderType c : SpiderType.values()) {
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
}
