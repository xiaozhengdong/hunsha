package org.guili.ecshop.util.chongwu;

import java.util.UUID;

/**
 * 随机工具类
 * @author Administrator
 *
 */
public class RandomUtil {

	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
