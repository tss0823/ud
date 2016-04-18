/**
 * 
 */
package com.usefullc.ud.common.utils;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * java 工具类
 * 
 * @author tangss
 * @2013年8月31日 @下午2:52:54
 */
public class JavaUtils {

    /**
     * 英文table名转java
     * 
     * @param name
     * @return
     */
    public static String tableNameToJava(String name) {
        StringTokenizer st = new StringTokenizer(name, "_");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            StringUtils.capitalize(token);
            sb.append(StringUtils.capitalize(token));
        }
        String str = sb.toString();
        str = StringUtils.substring(str, 0, 1).toLowerCase() + StringUtils.substring(str, 1);
        return str;
    }

    public static void main(String[] args) {
        String str = "COMIN_INFO";
        System.out.println(tableNameToJava(str));
    }
}
