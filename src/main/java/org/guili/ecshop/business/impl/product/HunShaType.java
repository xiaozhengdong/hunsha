package org.guili.ecshop.business.impl.product;

/**
 * 婚纱风格类型
 * @author Administrator
 *
 */
public enum HunShaType {
	Zhong_Shi("中式风格", 1,1), 
	Zhong_Guo_Hong("中国红", 2,2), 
	Han_Shi("韩式风格", 3,3),
	Wei_Mei("唯美韩式", 4,4),
	Ou_Mei("欧美风格", 5,5),
	Gu_Dian("古典风格", 6,6),
	Huai_Jiu("怀旧复古风格", 7,7),
	Xing_Gan("性感风格", 8,8),
	Qing_Xing("小清新风格", 9,9),
	Shi_Shang("时尚风格", 10,10),
	Qing_Lv("情侣装风格", 11,11),
	Xue_Sheng("学生装风格", 12,12),
	Gu_Zhuang("古装武侠风格", 13,13),
	Ge_Xing("个性创意风格", 14,14),
	;
    // 成员变量
    private String name;
    private int id;
    private int index;

    // 构造方法
    private HunShaType(String name, int index,int id) {
        this.name = name;
        this.index = index;
        this.id=id;
    }

    // 普通方法
    public static String getName(int id) {
        for (HunShaType c : HunShaType.values()) {
            if (c.getId() == id) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
