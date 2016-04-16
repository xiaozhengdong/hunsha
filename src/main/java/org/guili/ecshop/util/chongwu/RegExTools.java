package org.guili.ecshop.util.chongwu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述： 正则匹配工具类
 * @author 作者 Zhengdong.xiao@vip.com
 * @created 2015-3-12 下午4:25:29
 * @version 1.0.0
 * @date 2015-3-12 下午4:25:29
 */
public class RegExTools {

    /**
     * 功能描述：判断传入字符串是否包含中文，
     * @result  包含中文 true,没有中文 false
     */
    public static boolean hasChinese(String source){
        int count = 0;      
        String regEx = "[\\u4e00-\\u9fa5]";      
        Pattern p = Pattern.compile(regEx);      
        Matcher m = p.matcher(source);      
       while (m.find()) {      
           for (int i = 0; i <= m.groupCount(); i++) {      
                count = count + 1;      
            }      
        }
       if(count>0){
           return true;
       }else {
           return false;
       }
    }
    //test
    public static void main(String[] args) {      
        //System.out.println(regEx);      
         String str = "111fdas";      
         System.out.println("包含中文： " + RegExTools.hasChinese(str));      
     }  
}
