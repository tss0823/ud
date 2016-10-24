/**
 * 
 */
package com.usefullc.ud.common.utils;

import java.io.StringWriter;
import java.util.*;

import com.usefullc.platform.common.utils.DateUtils;
import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;

/**
 * velocity 模板工具类
 * @author tangss
 *
 * @2013年8月31日 @下午4:30:32
 */
public class VelocityRenderUtils {
	static String  LOGGER_NAME = "velexample";
	private  static Logger log = org.slf4j.LoggerFactory.getLogger(LOGGER_NAME);
	
	static{
		
		
	}
	
	/**
	 * 合并模板并获得内容
	 * @param paramMap
	 * @param templateDir
	 * @param templateFileName
	 * @return
	 */
	public static String getContent(Map<String,Object> paramMap,String templateDir,String templateFileName){
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.Log4JLogChute" );
		engine.setProperty("runtime.log.logsystem.log4j.logger",
				LOGGER_NAME);
		engine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, false);
		engine.setProperty(RuntimeConstants.INPUT_ENCODING, "utf-8");
		engine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "utf-8");
		
		log.info("templateDir="+templateDir);
		log.info("templateFileName="+templateFileName);
		engine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,templateDir);
		engine.init();
		VelocityContext context = new VelocityContext(paramMap);
		Template template = null;
		try{
		   template = engine.getTemplate(templateFileName);
		}
		catch( Exception e ){
		  log.error("getTemplate error! ",e);
		  throw new RuntimeException(e);
		}
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		return sw.toString();
	}

	public static void main(String[] args) {
		Map<String,Object> params = new HashedMap();
		ApplicationBo applicationBo = new ApplicationBo();
		applicationBo.setCnName("红包");
		applicationBo.setEnName("hongbao");
		applicationBo.setPackageName("com.yuntao.hongbao");
		applicationBo.setCreator("shengshan.tang");
		EntityBo entityBo = new EntityBo();
        applicationBo.addEntityBo(entityBo);
		entityBo.setCnName("用户");
		entityBo.setEnName("user");
		entityBo.setTableName("user");
		entityBo.setUpperEntityEnName("User");

		//properties
		PropertyBo propertyBo = new PropertyBo();
		propertyBo.setEnName("id");
		propertyBo.setColumnName("id");
		propertyBo.setCnName("ID");
        propertyBo.setColumnType("java.lang.Long");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
		entityBo.addProp(propertyBo);
		propertyBo.setEnName("accountNo");
		propertyBo.setColumnName("accountNo");
		propertyBo.setCnName("账号");
		propertyBo.setColumnType("java.lang.String");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
		entityBo.addProp(propertyBo);
		propertyBo.setEnName("pwd");
		propertyBo.setColumnName("pwd");
		propertyBo.setCnName("密码");
		propertyBo.setColumnType("java.lang.String");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
		entityBo.addProp(propertyBo);
		propertyBo.setEnName("type");
		propertyBo.setColumnName("type");
		propertyBo.setCnName("类型");
		propertyBo.setColumnType("java.lang.Integer");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
		entityBo.addProp(propertyBo);
		propertyBo.setEnName("gmtCreate");
		propertyBo.setColumnName("gmtCreate");
		propertyBo.setCnName("创建时间");
		propertyBo.setColumnType("java.util.Date");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
		entityBo.addProp(propertyBo);
		propertyBo.setEnName("gmtModify");
		propertyBo.setColumnName("gmtModify");
		propertyBo.setCnName("修改时间");
		propertyBo.setColumnType("java.util.Date");
		entityBo.addProp(propertyBo);

		propertyBo = new PropertyBo();
        entityBo.addProp(propertyBo);
		propertyBo.setEnName("delState");
		propertyBo.setColumnName("delState");
		propertyBo.setCnName("删除状态");
        propertyBo.setColumnType("java.util.Integer");
		entityBo.addProp(propertyBo);


		String time = DateUtils.getDate(new Date(), "yyyy-MM-dd HH");
		params.put("time", time);
		params.put("author", applicationBo.getCreator());
		params.put("packageName", applicationBo.getPackageName());
		params.put("appEnName", applicationBo.getEnName());
		String packagePath = applicationBo.getPackageName().replaceAll("\\.", "/");
		params.put("packagePath", packagePath);


		params.put("bo", entityBo);
		params.put("entityCnName", entityBo.getCnName());
		params.put("entityEnName", entityBo.getEnName());
		String upperEntityEnName = StringUtils.capitalize(entityBo.getEnName());
		params.put("upperEntityEnName", upperEntityEnName);
		params.put("tableName", entityBo.getTableName());
		params.put("appBo", applicationBo);
//		params.put("upperTableName", entityBo.getTableName().toUpperCase());



		String templateFileDir = "/u01/workspace/template2/_appEnName_-dal/src/main/resources/mapper";
		String fileName = "_upperEntityEnName_Mapper.xml";

		String str = getContent(params,templateFileDir,fileName);
		System.out.printf("file start\n");
		System.out.printf(str);
		System.out.printf("file end\n");
	}
}
