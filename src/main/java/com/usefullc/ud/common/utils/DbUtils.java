/**
 * 
 */
package com.usefullc.ud.common.utils;


import com.usefullc.platform.common.utils.ConfigUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 数据库工具类
 * @author tangss
 *
 * @2013年8月31日 @下午2:53:40
 */
public class DbUtils {

	/**
	 * java骆驼命名转table名
	 * @param name
	 * @return
	 */
	public static String javaToTableName(String name){
		String isBlankLine = ConfigUtils.getValue("gen.isBlankLine");
		if(!StringUtils.equals(isBlankLine,"yes")){
			return name;
		}
		StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < name.length(); i++){
	    	char ch = name.charAt(i);
	    	int inter = ch;
	    	if(inter >= 65 && inter <= 90){
	    		sb.append("_");
	    		ch = (char)(inter + 32);
	    	}
	    	sb.append(ch);
	    }
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = "tangShengShan";
		System.out.println(javaToTableName(str));
	}
}
