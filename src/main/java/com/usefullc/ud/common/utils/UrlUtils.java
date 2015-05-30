/**
 * 
 */
package com.usefullc.ud.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.MapUtils;

/**
 * @author tangss
 *
 * @2013年9月3日 @下午3:30:14
 */
public class UrlUtils {

	public static void execute(String urlString,Map<String,String> dataMap) throws IOException{
		long begintime = System.currentTimeMillis();
        URL url = new URL(urlString);
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setDoOutput(true);
        urlcon.setRequestMethod("GET");
        if(MapUtils.isEmpty(dataMap)){
        	OutputStream os = urlcon.getOutputStream();
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        	Set<Entry<String, String>> set = dataMap.entrySet();
        	StringBuilder sb = new StringBuilder();
        	for(Entry<String, String> entry : set){
        		String key = entry.getKey();
        		String value = entry.getValue();
        		sb.append(key+"="+value+"&");
        	}
        	String str = sb.toString();
        	str = str.substring(0,str.length()-1);
        	bw.write(str);
        	bw.flush();
        	bw.close();
        }
        
        urlcon.connect();         //获取连接
        InputStream is = urlcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while((l=buffer.readLine())!=null){
            bs.append(l).append("\n");
        }
        System.out.println(bs.toString());
       
        //System.out.println(" content-encode："+urlcon.getContentEncoding());
        //System.out.println(" content-length："+urlcon.getContentLength());
        //System.out.println(" content-type："+urlcon.getContentType());
        //System.out.println(" date："+urlcon.getDate());
             
        System.out.println("总共执行时间为："+(System.currentTimeMillis()-begintime)+"毫秒");
	}
	
	public static void main(String[] args) {
		try {
			//execute("http://localhost:8080/user/enter_edit.htm");
			//String str = URLDecoder.decode("ui.object=%2fcontent%2fpackage%5b%40name%3d%27%e5%85%b3%e9%94%ae%e8%bf%90%e8%90%a5%e6%8c%87%e6%a0%87%e5%88%86%e6%9e%90(%e6%97%a5%e5%8e%86%e5%b9%b4%e5%ba%a6)%27%5d%2freport%5b%40name%3d%27%e4%ba%a7%e5%93%81%e6%af%8f%e6%97%a5%e4%b8%80%e8%a7%88(%e6%97%a5%e5%8e%86%e5%b9%b4%e5%ba%a6)%27%5d");
//			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
