/**
 * 
 */
package com.usefullc.ud.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

/**
 * @author tangss
 *
 * @2013年10月10日 @上午11:26:05
 */
public class ParseResourceLink {


	private static Map<String,String> linkMap = new HashMap<String,String>();

	@SuppressWarnings("unchecked")
	private static void parserXml() {
		InputStream is = ParseResourceLink.class.getResourceAsStream("/resource_link.xml");
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for(Element el : elements){  //loop link or links
				String link = el.attributeValue("value");
				link = "/"+link;
				String role = el.attributeValue("role");
				if(el.getName().equals("link")){
					linkMap.put(link, role);
					continue;
				}
				List<Element> childElements = el.elements();
				for(Element childEl : childElements){  //loop link 
					String childLink = childEl.attributeValue("value");
					String childRole = el.attributeValue("role");
					if(StringUtils.isEmpty(childRole)){
						childRole = role;
					}
					childLink = link + "/"+childLink;
					linkMap.put(childLink, childRole);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得资源链接
	 * @return
	 */
	public static Map<String,String> getLinkMap(){
		if(linkMap.isEmpty()){
			parserXml();
		}
		return linkMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> linkMap = getLinkMap();
		System.out.println(linkMap);
	}
}
