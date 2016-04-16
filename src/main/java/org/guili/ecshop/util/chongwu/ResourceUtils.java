package org.guili.ecshop.util.chongwu;

import java.util.ResourceBundle;

import org.guili.ecshop.util.ResourceUtil;

/**
 * 
 * @author zhengdong.xiao
 */
public class ResourceUtils {
	public static ResourceBundle	chongwu			= ResourceBundle.getBundle("config/chongwu");
	public static  String		upload_folder_path	= ResourceUtil.getValue(chongwu, "upload.folder.path");
	public static  String		proxyips			= ResourceUtil.getValue(chongwu, "proxyips");
	/**
	 * 获取页数(如果是增量更新，返回增量更新的页数)
	 * @param pageNum
	 * @return
	 */
	public static String getFolderPath(){
		return upload_folder_path;
	}
	public static void main(String[] args) {
		System.out.println(ResourceUtils.getFolderPath());
	}
}
