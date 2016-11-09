/**
 * 
 */
package com.usefullc.ud.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.usefullc.ud.common.utils.ReplaceUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;
import com.usefullc.ud.common.bo.ValidItemBo;
import com.usefullc.ud.common.bo.ValidPropertyBo;
import com.usefullc.ud.common.constant.AppDataConstant;
import com.usefullc.ud.common.utils.DbUtils;
import com.usefullc.ud.common.utils.VelocityRenderUtils;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.domain.HisEntity;
import com.usefullc.ud.domain.ValidRule;
import com.usefullc.platform.common.utils.ConfigUtils;
import com.usefullc.platform.common.utils.DateUtils;
import com.usefullc.platform.common.utils.RegexUtils;
import com.usefullc.platform.common.utils.StrUtils;
import com.usefullc.platform.common.utils.StrUtils.StrSeparator;

/**
 * 应用构建
 * 
 * @author tangss
 * @2013年8月31日 @下午4:55:22
 */
public class ApplicationBuilder {

    /**
     * 不用veclotiy渲染的模版后缀 集合
     */
    private final List<String> postfixList          = new ArrayList<String>();

    /**
     * 单个生成文件目录集合
     */
    private final List<String> singleFileModuleList = new ArrayList<String>();

    private final String       udcodeStart          = "user definition code start";
    private final String       udcodeEnd            = "user definition code end";

    {
        postfixList.add("vm");
        postfixList.add("css");
        postfixList.add("js");
        postfixList.add("png");
        postfixList.add("jpg");
        postfixList.add("gif");

        String singlefileModule = ConfigUtils.getValue("gen.singlefileModule");
        String sfmStrs[] = singlefileModule.split(",");
        for (String sfm : sfmStrs) {
            singleFileModuleList.add(sfm);
        }
    }

    private Logger             log                  = LoggerFactory.getLogger(ApplicationBuilder.class);

    private String getTemplatePath(Attachment attachment) {
        String basePath = System.getProperty("java.io.tmpdir") + File.separator + AppDataConstant.TEMPLATE_MODULE;
        String fileName = attachment.getName();
        String templateFileName = FilenameUtils.getBaseName(fileName);
        String templateDir = basePath + File.separator + templateFileName;
        File templateDirFile = new File(templateDir);
        if (!templateDirFile.exists()) { // 不存在解压
            com.usefullc.platform.common.utils.FileUtils.unzipFile(attachment.getContent(), templateDir);
        }
        return templateDir;
    }

    private String getGenPath(String enName) {
        String sysGenPath = System.getProperty("java.io.tmpdir") + File.separator + AppDataConstant.GEN_PATH_MODULE;
        String gtime = DateUtils.getCurTime("yyyyMMddHHmmss");
        String genPath = StrUtils.join(StrSeparator.SEPARATOR, sysGenPath, enName, gtime);
        return genPath;
    }

    private Attachment genAttachment(String genPath) {
        Attachment attachment = new Attachment();
        String genZipPath = genPath + ".zip";
        com.usefullc.platform.common.utils.FileUtils.zipFile(genPath, genZipPath, null);
        byte[] content = null;
        try {
            File zipFile = new File(genZipPath);
            attachment.setName(zipFile.getName());
            content = FileUtils.readFileToByteArray(zipFile);
            attachment.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    public Attachment buildApp(ApplicationBo appBo) {
        Map<String, Object> globalParamMap = new HashMap<String, Object>();
        Map<String, String> globalReplaceMap = new HashMap<String, String>();

        String appShortName = appBo.getShortName();
        String templateDir = getTemplatePath(appBo.getTemplateBo().getAttachment());
        String genPath = getGenPath(appBo.getEnName());

        File templateDirFile = new File(templateDir);

        String time = DateUtils.getDate(new Date(), "yyyy-MM-dd HH");
        String packageName = appBo.getPackageName();
        globalParamMap.put("time", time);
        globalParamMap.put("author", appBo.getCreator());
        globalParamMap.put("packageName", packageName);
        globalParamMap.put("appEnName", appBo.getEnName());
        String packagePath = packageName.replaceAll("\\.", "/");
        globalReplaceMap.put("packagePath", packagePath);
        globalReplaceMap.put("appEnName", appBo.getEnName());
        globalParamMap.put("packagePath", packagePath);
        globalParamMap.put("dbType", appBo.getDbConfigure().getType());
        globalParamMap.put("tableSpace", appBo.getDbConfigure().getTableSpace());
        globalParamMap.put("jdbc", appBo.getDbConfigure());



        List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
        List<Map<String, String>> replaceList = new ArrayList<Map<String, String>>();
        List<EntityBo> entityBoList = appBo.getEntityBoList();
        for (EntityBo entityBo : entityBoList) {
            String entityCnName = entityBo.getCnName();
            String entityEnName = entityBo.getEnName();
            String upperEntityEnName = StringUtils.capitalize(entityEnName);
            String tableName = DbUtils.javaToTableName(entityEnName);
            String shortName = entityBo.getShortName();
            if (StringUtils.isEmpty(shortName)) {
                shortName = appShortName;
            }
            if (StringUtils.isNotEmpty(shortName)) {
                tableName = shortName + "_" + tableName;
            }
            entityBo.setUpperEntityEnName(upperEntityEnName);
            entityBo.setTableName(tableName);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("bo", entityBo);
            paramMap.put("entityCnName", entityCnName);
            paramMap.put("entityEnName", entityEnName);
            paramMap.put("upperEntityEnName", upperEntityEnName);
            paramMap.put("tableName", tableName);
            paramMap.put("upperTableName", tableName.toUpperCase());

            // replace目录文件用
            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("entityEnName", entityEnName);
            replaceMap.put("entityCnName", entityCnName);
            replaceMap.put("upperEntityEnName", upperEntityEnName);
            replaceMap.putAll(globalReplaceMap);
            replaceList.add(replaceMap);

            List<PropertyBo> propBoList = entityBo.getPropList();
            for (PropertyBo propertyBo : propBoList) {
                // String columnType = DbTypeMysqlUtils.getValue(propertyBo.getDataType());
                String enName = propertyBo.getEnName();
                String upperEnName = StringUtils.capitalize(enName);
                String columnName = DbUtils.javaToTableName(enName);
                // propertyBo.setColumnType(columnType);
                propertyBo.setUpperEnName(upperEnName);
                propertyBo.setColumnName(columnName);
                propertyBo.setUpperColumnName(columnName.toUpperCase());
            }
            //modify at 2016-08-4
            globalParamMap.put("entityBoList", entityBoList);  //为了mybatis-config dalConfig 能拿到实体

            paramMap.putAll(globalParamMap);
            paramList.add(paramMap);

        }

        // 构建multi
        for (int i = 0; i < paramList.size(); i++) {
            Map<String, Object> paramMap = paramList.get(i);
            String multiPath = "";  //ConfigUtils.getValue("gen.multiPath");
            String abstractMultiPath = templateDir + File.separator + multiPath;
            try {
                File multiTemplateFile = new File(abstractMultiPath);
                //templateDir= 模板路径
                //genPath=生成径路
                operateFile(templateDir, paramMap, replaceList.get(i), multiTemplateFile, genPath, true, false);
            } catch (IOException e) {
                throw new RuntimeException("operateFile failed! ", e);
            }
        }
        // 构建single
        globalParamMap.put("appBo", appBo);
        try {
            operateFile(templateDir, globalParamMap, globalReplaceMap, templateDirFile, genPath, false, false);
        } catch (IOException e) {
            throw new RuntimeException("operateFile failed! ", e);
        }
        // 返回
        return genAttachment(genPath);

    }

    public Attachment buildAppSingle(ApplicationBo appBo) {
        Map<String, Object> globalParamMap = new HashMap<String, Object>();
        Map<String, String> globalReplaceMap = new HashMap<String, String>();

        String templateDir = getTemplatePath(appBo.getTemplateBo().getAttachment());
        String genPath = getGenPath(appBo.getEnName());

        File templateDirFile = new File(templateDir);

        String appShortName = appBo.getShortName();
        String time = DateUtils.getDate(new Date(), "yyyy-MM-dd HH");
        String packageName = appBo.getPackageName();
        globalParamMap.put("time", time);
        globalParamMap.put("author", appBo.getCreator());
        globalParamMap.put("packageName", packageName);
        globalParamMap.put("appEnName", appBo.getEnName());
        String packagePath = packageName.replaceAll("\\.", "/");
        globalReplaceMap.put("appEnName", appBo.getEnName());
        globalReplaceMap.put("packagePath", packagePath);
        globalParamMap.put("packagePath", packagePath);
        globalParamMap.put("dbType", appBo.getDbConfigure().getType());

        List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
        List<Boolean> hisEList = new ArrayList<Boolean>();
        List<Map<String, String>> replaceList = new ArrayList<Map<String, String>>();
        List<EntityBo> entityBoList = appBo.getEntityBoList();

        // app 版本号
        int appVer = appBo.getVer();
        for (EntityBo entityBo : entityBoList) {
            // 必须是最新版本号的
            if (entityBo.getVer() == null || entityBo.getVer().intValue() != appVer) {
                continue;
            }
            String entityCnName = entityBo.getCnName();
            String entityEnName = entityBo.getEnName();
            String upperEntityEnName = StringUtils.capitalize(entityEnName);
            String tableName = DbUtils.javaToTableName(entityEnName);
            String shortName = entityBo.getShortName();
            if (StringUtils.isEmpty(shortName)) {
                shortName = appShortName;
            }
            if (StringUtils.isNotEmpty(shortName)) {
                tableName = shortName + "_" + tableName;
            }
            entityBo.setUpperEntityEnName(upperEntityEnName);
            entityBo.setTableName(tableName);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.putAll(globalParamMap); // 局部变量中，添加所有全局变量，遇到相同的，则局部变量覆盖
            paramMap.put("bo", entityBo);
            paramMap.put("entityCnName", entityCnName);
            paramMap.put("entityEnName", entityEnName);
            paramMap.put("upperEntityEnName", upperEntityEnName);
            paramMap.put("tableName", tableName);
            paramMap.put("upperTableName", tableName.toUpperCase());

            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("entityCnName", entityCnName);
            replaceMap.put("upperEntityEnName", upperEntityEnName);
            replaceMap.putAll(globalReplaceMap);
            replaceList.add(replaceMap);

            List<PropertyBo> propBoList = entityBo.getPropList();
            for (PropertyBo propertyBo : propBoList) {
                // 必须是最新版本号的
                if (propertyBo.getVer().intValue() != appVer) {
                    continue;
                }
                // String columnType = DbTypeMysqlUtils.getValue(propertyBo.getDataType());
                String enName = propertyBo.getEnName();
                String upperEnName = StringUtils.capitalize(enName);
                String columnName = DbUtils.javaToTableName(enName);
                // propertyBo.setColumnType(columnType);
                propertyBo.setUpperEnName(upperEnName);
                propertyBo.setColumnName(columnName);
                propertyBo.setUpperColumnName(columnName.toUpperCase());
            }

            paramList.add(paramMap);

            // 获得 hasHis 是否有历史
            boolean hasHis = false;
            for (HisEntity hisE : appBo.getPreHisEntityList()) {
                if (hisE.getMainId().equals(entityBo.getId())) {
                    hasHis = true;
                    break;
                }
            }
            hisEList.add(hasHis);
        }
        // 构建multi
        for (int i = 0; i < paramList.size(); i++) {
            Map<String, Object> paramMap = paramList.get(i);
            String multiPath = ConfigUtils.getValue("gen.multiPath");
            String abstractMultiPath = templateDir + File.separator + multiPath;
            try {
                File multiTemplateFile = new File(abstractMultiPath);
                operateFile(templateDir, paramMap, replaceList.get(i), multiTemplateFile, genPath, true,
                            hisEList.get(i));
            } catch (IOException e) {
                throw new RuntimeException("operateFile failed! ", e);
            }
        }
        // 构建single
        globalParamMap.put("appBo", appBo);
        try {
            operateFile(templateDir, globalParamMap, globalReplaceMap, templateDirFile, genPath, false, true);
        } catch (IOException e) {
            throw new RuntimeException("operateFile failed! ", e);
        }

        // 返回
        return genAttachment(genPath);

    }

    public Attachment buildValidAppSingle(ApplicationBo appBo) {
        Map<String, Object> globalParamMap = new HashMap<String, Object>();
        Map<String, String> globalReplaceMap = new HashMap<String, String>();

        String templateDir = getTemplatePath(appBo.getTemplateBo().getAttachment());
        String genPath = getGenPath(appBo.getEnName());

        File templateDirFile = new File(templateDir);

        String time = DateUtils.getDate(new Date(), "yyyy-MM-dd HH");
        String packageName = appBo.getPackageName();
        globalParamMap.put("time", time);
        globalParamMap.put("author", appBo.getCreator());
        globalParamMap.put("packageName", packageName);
        globalParamMap.put("appEnName", appBo.getEnName());
        String packagePath = packageName.replaceAll("\\.", "/");
        globalReplaceMap.put("packagePath", packagePath);
        globalReplaceMap.put("appEnName", appBo.getEnName());
        globalParamMap.put("packagePath", packagePath);

        List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
        List<Map<String, String>> replaceList = new ArrayList<Map<String, String>>();
        List<ValidItemBo> validItemBoList = appBo.getValidItemBoList();

        // 获得规则集合
        List<ValidRule> validRuleList = appBo.getValidRuleList();

        for (ValidItemBo validItemBo : validItemBoList) {
            String entityCnName = validItemBo.getCnName();
            String entityEnName = validItemBo.getEnName();
            String upperEntityEnName = StringUtils.capitalize(entityEnName);
            String tableName = DbUtils.javaToTableName(entityEnName);
            validItemBo.setUpperEntityEnName(upperEntityEnName);
            validItemBo.setTableName(tableName);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.putAll(globalParamMap); // 局部变量中，添加所有全局变量，遇到相同的，则局部变量覆盖
            paramMap.put("validBo", validItemBo);
            paramMap.put("entityCnName", entityCnName);
            paramMap.put("entityEnName", entityEnName);
            paramMap.put("upperEntityValidEnName", upperEntityEnName);
            paramMap.put("tableName", tableName);
            paramMap.put("upperTableName", tableName.toUpperCase());

            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("entityCnName", entityCnName);
            replaceMap.put("entityValidEnName", entityEnName);
            replaceMap.put("upperEntityValidEnName", upperEntityEnName);
            replaceMap.putAll(globalReplaceMap);
            replaceList.add(replaceMap);

            List<ValidPropertyBo> propBoList = validItemBo.getPropList();

            // 校验规则处理
            for (ValidPropertyBo propertyBo : propBoList) {
                List<String> validList = new ArrayList<String>();
                List<String> jsRuleValidList = new ArrayList<String>();
                List<String> jsMsgValidList = new ArrayList<String>();
                // String columnType = DbTypeMysqlUtils.getValue(propertyBo.getDataType());
                String enName = propertyBo.getEnName();
                String upperEnName = StringUtils.capitalize(enName);
                String columnName = DbUtils.javaToTableName(enName);
                // propertyBo.setColumnType(columnType);
                propertyBo.setUpperEnName(upperEnName);
                propertyBo.setColumnName(columnName);
                propertyBo.setUpperColumnName(columnName.toUpperCase());

                if (!propertyBo.getIsNull()) { // 非空校验
                    String msgForNull = propertyBo.getMsgForNull();
                    if (StringUtils.isEmpty(msgForNull)) {
                        msgForNull = propertyBo.getMsgName() + "不能为空";
                    }
                    String validStr = "@NotNull(message = \"" + msgForNull + "\")";
                    validList.add(validStr);
                    jsRuleValidList.add("required:true");
                    jsMsgValidList.add("required:\"" + msgForNull + "\"");
                }
                // 长度校验
                if (StringUtils.isNotEmpty(propertyBo.getLength())) {
                    String msgforLen = propertyBo.getMsgForLen();
                    int len = Integer.valueOf(propertyBo.getLength());
                    if (StringUtils.isEmpty(msgforLen)) {
                        msgforLen = propertyBo.getMsgName() + "的长度不能超过" + len + "字符";
                    }
                    String validStr = "@Size(min=1,max = " + len + ", message = \"" + msgforLen + "\")";
                    validList.add(validStr);
                    jsRuleValidList.add("maxLength:" + len);
                    jsMsgValidList.add("maxLength:\"" + msgforLen + "\"");
                }
                // 正则校验
                if (propertyBo.getRuleId() != null) {
                    for (ValidRule validRule : validRuleList) {
                        if (propertyBo.getRuleId().equals(validRule.getId())) {
                            String javaRegex = validRule.getJavaRegex();
                            String msgForRule = propertyBo.getMsgForRule();
                            if (StringUtils.isEmpty(msgForRule)) {
                                msgForRule = propertyBo.getMsgName() + "的格式不合法";
                            }
                            String validStr = "@Pattern(regexp=\"" + javaRegex + "\" message=\"" + msgForRule + "\")";
                            validList.add(validStr);
                            String jsRegex = validRule.getJsRegex();
                            jsRuleValidList.add("regex:\"" + jsRegex + "\"");
                            jsMsgValidList.add("regex:\"" + msgForRule + "\"");
                            break;
                        }
                    }
                }
                propertyBo.setValidList(validList);
                propertyBo.setJsRuleValidList(jsRuleValidList);
                propertyBo.setJsMsgValidList(jsMsgValidList);
            }
            paramList.add(paramMap);

        }
        // 构建multi
        for (int i = 0; i < paramList.size(); i++) {
            Map<String, Object> paramMap = paramList.get(i);
            String multiPath = ConfigUtils.getValue("gen.multiPath");
            String abstractMultiPath = templateDir + File.separator + multiPath;
            try {
                File multiTemplateFile = new File(abstractMultiPath);
                operateFile(templateDir, paramMap, replaceList.get(i), multiTemplateFile, genPath, true, false);
            } catch (IOException e) {
                throw new RuntimeException("operateFile failed! ", e);
            }
        }
        // 构建single
        globalParamMap.put("appBo", appBo);
        try {
            operateFile(templateDir, globalParamMap, globalReplaceMap, templateDirFile, genPath, false, false);
        } catch (IOException e) {
            throw new RuntimeException("operateFile failed! ", e);
        }

        // 返回
        return genAttachment(genPath);

    }

    private void operateFile(String baseTemplateDir, Map<String, Object> paramMap, Map<String, String> replaceMap,
                             File dirFile, String genPath, boolean isMulti, boolean hasHis) throws IOException {
        File[] files = dirFile.listFiles();
        if (files == null || files.length == 0) { // 目录下没有文件
            String filePath = dirFile.getPath();
            String oppsitePath = filePath.substring(baseTemplateDir.length());
            log.info("oppsitePath=" + oppsitePath);
            String newFilePath = genPath + File.separator + oppsitePath;
            newFilePath = ReplaceUtils.replaceAll(newFilePath, replaceMap);
            log.info("newFilePath=" + newFilePath);
            new File(newFilePath).mkdirs();
        }
        for (File file : files) {
            String fileName = file.getName();
            String filePath = file.getPath();
            String templateDir = file.getParent();
            String oppsitePath = filePath.substring(baseTemplateDir.length());
            if (file.isFile()) { // 文件处理
                // 防止人工错误
                if (isMulti && fileName.indexOf("_") == -1) { // 多个规则，遇到非动态字符则过滤
                    continue;
                } else if (!isMulti && fileName.indexOf("_") != -1) { // 单个规则，遇到动态字符则过滤
                    continue;
                }
                log.info("oppsitePath=" + oppsitePath);
                String newFilePath = genPath + File.separator + oppsitePath;
                newFilePath = ReplaceUtils.replaceAll(newFilePath, replaceMap);
                log.info("newFilePath=" + newFilePath);
                File newFile = new File(newFilePath);
                // 后缀过滤，不解析，直接复制内容
                String extension = FilenameUtils.getExtension(fileName);
                if (postfixList.contains(extension)) {
                    byte[] byteArray = FileUtils.readFileToByteArray(file);
                    FileUtils.writeByteArrayToFile(newFile, byteArray);
                } else {
                    // velocity模版解析
                    String parentPath = file.getParent();
                    int start = parentPath.lastIndexOf(File.separator);
                    int end = parentPath.length();
                    String fileModule = filePath.substring(start + 1, end);
                    if (hasHis && !singleFileModuleList.contains(fileModule)) {
                        continue;
                    }

                    if (fileName.endsWith("mybatis-config.xml") || fileName.endsWith("DalConfig.java")) {
                        System.out.printf("");
                    }

                    String fileContent = VelocityRenderUtils.getContent(paramMap, templateDir, file.getName());
                    // 对于mapper文件特殊处理
                    if (fileName.endsWith("Mapper.xml")) {
                        StringTokenizer st = new StringTokenizer(fileContent, "\n");
                        String lineText = null;
                        StringBuilder sb = new StringBuilder();
                        while (st.hasMoreElements()) {
                            lineText = st.nextToken();
                            sb.append(lineText);
                            sb.append("\n");
                            // 遇到开始自定义code就添加老文件用户自定义code
                            if (StringUtils.indexOf(lineText, udcodeStart) != -1) {
                                String udCode = getMapperUdCode(newFile);
                                if (StringUtils.isNotEmpty(udCode)) {
                                    sb.append(udCode);
                                    sb.append("\n");
                                }
                            }
                        }
                        fileContent = sb.toString();
                    }
                    FileUtils.writeStringToFile(newFile, fileContent, "utf-8");
                }
            } else { // 目录处理
                operateFile(baseTemplateDir, paramMap, replaceMap, file, genPath, isMulti, hasHis);
            }
        }
    }

    private String getMapperUdCode(File file) {
        String fileContent = null;
        if (!file.exists()) {
            return "";
        }
        try {
            fileContent = FileUtils.readFileToString(file, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer st = new StringTokenizer(fileContent, "\n");
        String lineText = null;
        boolean isStart = false;
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreElements()) {
            lineText = st.nextToken();
            if (StringUtils.indexOf(lineText, udcodeEnd) != -1) {
                break;
            }
            if (isStart && !StringUtils.equals(StringUtils.trim(lineText), "\t")) {
                sb.append(lineText);
                sb.append("\n");
            }
            if (StringUtils.indexOf(lineText, udcodeStart) != -1) {
                isStart = true;
            }
        }
        return sb.toString();
    }

    private void clearFoder(String clearFilePath) throws IOException {
        // FileUtils.deleteDirectory(new File(clearFilePath));
    }
}
