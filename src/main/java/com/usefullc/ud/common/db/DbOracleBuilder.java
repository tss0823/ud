/**
 * 
 */
package com.usefullc.ud.common.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;
import com.usefullc.ud.common.utils.DbTypeOracleUtils;
import com.usefullc.ud.common.utils.DbUtils;
import com.usefullc.ud.common.utils.JavaUtils;
import com.usefullc.ud.domain.HisProperty;
import com.usefullc.ud.domain.Property;

/**
 * @author tangss
 * @2013年8月30日 @上午11:23:31
 */
public class DbOracleBuilder implements DbBuilder {

    @Override
    public String buildAllTableSql(ApplicationBo appBo, List<EntityBo> entityList) {
        StringBuilder sb = new StringBuilder();
        for (EntityBo entityBo : entityList) {
            String str = this.buildTableSql(appBo, entityBo, entityBo.getPropList());
            sb.append(str);
        }
        return sb.toString();
    }

    @Override
    public String buildTableSql(ApplicationBo appBo, EntityBo entity, List<PropertyBo> propList) {
        String sql = "";
        String shortName = appBo.getShortName();
        if (!StringUtils.isEmpty(entity.getShortName())) {
            shortName = entity.getShortName();
        }
        String tableName = DbUtils.javaToTableName(entity.getEnName());
        if (!StringUtils.isEmpty(shortName)) {
            tableName = shortName + "_" + tableName;
        }
        tableName = tableName.toUpperCase();

        String tableSpace = appBo.getDbConfigure().getTableSpace();

        // 定义字符构建对象
        StringBuilder sb = new StringBuilder();

        // 定义字段注释字符构建对象
        StringBuilder commentSb = new StringBuilder();

        // 定义序列号字符构建对象
        StringBuilder seqSb = new StringBuilder();

        sb.append("-- Create table");
        sb.append("\n");

        // 序列号
        seqSb.append("create sequence SEQ_");
        seqSb.append(tableName);
        seqSb.append("\n");
        seqSb.append("minvalue 1 ");
        seqSb.append("\n");
        seqSb.append("maxvalue 99999999999999");
        seqSb.append("\n");
        seqSb.append("start with 1");
        seqSb.append("\n");
        seqSb.append("increment by 1");
        seqSb.append("\n");
        seqSb.append("nocache;");
        seqSb.append("\n");

        sb.append("CREATE TABLE ");
        sb.append(tableName);
        sb.append("(");
        String primaryKey = null;
        int len = propList.size();
        for (int i = 0; i < len; i++) {
            Property prop = propList.get(i);
            sb.append("\n  ");
            String columnName = DbUtils.javaToTableName(prop.getEnName()).toUpperCase();
            sb.append(columnName);
            sb.append(" ");
            String dataType = prop.getDataType();
            String columnType = DbTypeOracleUtils.getDbValue(dataType, prop.getLength());
            sb.append(columnType);
            if (!StringUtils.isEmpty(prop.getLength())) {
                sb.append("(");
                sb.append(prop.getLength());
                sb.append(")");
            }
            sb.append(" ");
            if (prop.getIsNull() != null && !prop.getIsNull()) {
                sb.append("NOT NULL");
                sb.append(" ");
            } else {
                if (!StringUtils.isEmpty(prop.getDefaultValue())) {
                    sb.append("DEFAULT ");
                    sb.append(prop.getDefaultValue());
                }
            }
            if (i != (len - 1)) { // 没有到最后一行
                sb.append(",");
            }
            sb.append("\n");

            // 添加comments
            commentSb.append("comment on column ");
            commentSb.append(tableName);
            commentSb.append(".");
            commentSb.append(columnName);
            commentSb.append("\n");
            commentSb.append("is ");
            commentSb.append("'");
            commentSb.append(prop.getCnName());
            commentSb.append("';");
            commentSb.append("\n");

            if (prop.getPrimaryKey()) {
                primaryKey = columnName;
            }

        }
        sb.append(")");
        sb.append("\n");
        sb.append("tablespace ");
        sb.append(tableSpace);
        sb.append("\n");
        sb.append("pctfree 10");
        sb.append("\n");
        sb.append("initrans 1");
        sb.append("\n");
        sb.append("maxtrans 255");
        sb.append("\n");
        sb.append("storage");
        sb.append("\n");
        sb.append("(");
        sb.append("\n");
        sb.append("initial 64K");
        sb.append("\n");
        sb.append("minextents 1");
        sb.append("\n");
        sb.append("maxextents unlimited");
        sb.append("\n");
        sb.append(");");
        sb.append("\n");
        sb.append("\n");

        sb.append(" -- Add comments to the table");
        sb.append("\n");
        sb.append("comment on table " + tableName + "");
        sb.append(" is '" + entity.getCnName() + "';");
        sb.append("\n");

        sb.append("-- Add comments to the columns ");
        sb.append("\n");
        sb.append(commentSb);

        sb.append("-- Create/Recreate primary, unique and foreign key constraints ");
        sb.append("\n");
        sb.append("alter table ");
        sb.append(tableName);
        sb.append("\n");
        String pkKeyName = "PK_" + tableName + "_" + primaryKey;
        if (pkKeyName.length() > 30) {
            int overLen = pkKeyName.length() - 30;
            pkKeyName = "PK_" + tableName.substring(0, tableName.length() - overLen) + "_" + primaryKey;
        }
        sb.append("add constraint " + pkKeyName + " primary key (" + primaryKey + ")");
        sb.append("\n");
        sb.append("using index");
        sb.append("\n");
        sb.append("tablespace WHZH_TS");
        sb.append("\n");
        sb.append("pctfree 10");
        sb.append("\n");
        sb.append("initrans 2");
        sb.append("\n");
        sb.append("maxtrans 255");
        sb.append("\n");
        sb.append("storage");
        sb.append("\n");
        sb.append("(");
        sb.append("\n");
        sb.append(" initial 64K");
        sb.append("\n");
        sb.append(" minextents 1");
        sb.append("\n");
        sb.append(" maxextents unlimited");
        sb.append("\n");
        sb.append(");");
        sb.append("\n");

        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(seqSb);

        sql = sb.toString();
        return sql;
    }

    /**
     * 构建删除
     * 
     * @param entity
     * @param propList
     * @return
     */
    @Override
    public String buildDelTableSql(ApplicationBo appBo, List<EntityBo> entityList) {
        String sql = "";

        // 定义删除表字符构建对象
        StringBuilder delSb = new StringBuilder();

        delSb.append("\n\n");
        delSb.append("declare");
        delSb.append("\n");
        delSb.append("num number;");
        delSb.append("\n");
        delSb.append("begin");

        String shortName = appBo.getShortName();
        for (EntityBo entity : entityList) {
            if (!StringUtils.isEmpty(entity.getShortName())) {
                shortName = entity.getShortName();
            }
            String tableName = DbUtils.javaToTableName(entity.getEnName());
            if (!StringUtils.isEmpty(shortName)) {
                tableName = shortName + "_" + tableName;
            }
            tableName = tableName.toUpperCase();

            String seqName = "SEQ_" + tableName;

            delSb.append("\n");
            delSb.append("select count(1) into num from user_tables where table_name = '");
            delSb.append(tableName);
            delSb.append("';");
            delSb.append("\n");
            delSb.append("if num > 0 then");
            delSb.append("\n");
            delSb.append("execute immediate 'drop table " + tableName + "'");
            delSb.append(";");
            delSb.append("\n");
            delSb.append(" end if;");
            delSb.append("\n");
            delSb.append("select count(1) into num from user_sequences where SEQUENCE_NAME = '");
            delSb.append(seqName);
            delSb.append("';");
            delSb.append("\n");
            delSb.append("if num > 0 then");
            delSb.append("\n");
            delSb.append("execute immediate 'drop sequence " + seqName + "'");
            delSb.append(";");
            delSb.append("\n");
            delSb.append(" end if;");
        }

        delSb.append("\n");
        delSb.append("end;");
        delSb.append("\n\n");
        sql = delSb.toString();
        return sql;
    }

    @Override
    public String buildTableAlertSql(String shortName, EntityBo entityBo) {
        StringBuilder prefixSb = new StringBuilder("ALTER TABLE ");
        // shortName 处理
        if (!StringUtils.isEmpty(entityBo.getShortName())) {
            shortName = entityBo.getShortName();
        }
        String tableName = DbUtils.javaToTableName(entityBo.getEnName());
        if (!StringUtils.isEmpty(shortName)) {
            tableName = shortName + "_" + tableName;
        }
        tableName = tableName.toUpperCase();
        prefixSb.append(tableName);

        // alert table 前缀
        String prefixStr = prefixSb.toString();

        StringBuilder sb = new StringBuilder();

        List<PropertyBo> propBoList = entityBo.getPropList();
        // 获得前一个历史版本记录
        List<HisProperty> preHisPropList = entityBo.getPreHisPropList();
        for (Iterator<PropertyBo> it = propBoList.iterator(); it.hasNext();) {
            PropertyBo propBo = it.next();
            for (Iterator<HisProperty> iter = preHisPropList.iterator(); iter.hasNext();) {
                HisProperty hisProp = iter.next();
                // 跟当前版本比较，如果存在，则移出
                if (propBo.equals(hisProp)) {
                    it.remove();
                    iter.remove();
                }
            }
        }
        int propSize = propBoList.size();
        int hisSize = preHisPropList.size();
        // 没有变化，则直接返回
        if (propSize == 0 && hisSize == 0) {
            return "";
        }
        // 删除历史中有，当前没有的column
        for (int i = 0; i < hisSize; i++) {
            HisProperty hisProp = preHisPropList.get(i);
            String columnName = DbUtils.javaToTableName(hisProp.getEnName()).toUpperCase();
            sb.append(prefixStr);
            sb.append(" drop column  " + columnName + " ");
            sb.append(";\n");
        }

        // 新增当前版本有，历史没有的column
        for (int i = 0; i < propSize; i++) {
            PropertyBo propBo = propBoList.get(i);
            sb.append(prefixStr);
            sb.append(" add  ");
            String columnName = DbUtils.javaToTableName(propBo.getEnName()).toUpperCase();
            sb.append(columnName);
            sb.append(" ");
            String dataType = propBo.getDataType();
            String columnType = DbTypeOracleUtils.getDbValue(dataType, propBo.getLength());
            sb.append(columnType);
            if (!StringUtils.isEmpty(propBo.getLength())) {
                sb.append("(");
                sb.append(propBo.getLength());
                sb.append(")");
            }
            sb.append(" ");
            if (propBo.getIsNull() != null && !propBo.getIsNull()) {
                sb.append("NOT NULL");
                sb.append(" ");
            } else {
                if (!StringUtils.isEmpty(propBo.getDefaultValue())) {
                    sb.append("DEFAULT ");
                    sb.append(propBo.getDefaultValue());
                }
            }
            sb.append(";\n");

            // 添加comments
            sb.append("comment on column ");
            sb.append(tableName);
            sb.append(".");
            sb.append(columnName);
            sb.append("\n");
            sb.append("is ");
            sb.append("'");
            sb.append(propBo.getCnName());
            sb.append("';");
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public List<String> getCreateTableSql(InputStream is) {
        List<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        boolean start = false;
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = br.readLine()) != null) {
                if (!start && !line.startsWith("create table")) {
                    continue;
                } else if (line.startsWith("create table")) {
                    start = true;
                } else if (start && line.startsWith("  add constraint")) {
                    sb.append(line);
                    sb.append("\n");
                    start = false;
                    // 处理table 业务
                    list.add(sb.toString());
                    sb.setLength(0);
                    continue;
                }
                sb.append(line);
                sb.append("\n");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public EntityBo parseSqlToBo(String shortName, String sql) {
        EntityBo bo = new EntityBo();

        int n = sql.indexOf("\n");
        // table 部分
        String tableStr = sql.substring(0, n);
        String tableName = tableStr.split(" ")[2].toLowerCase();
        if (!StringUtils.isEmpty(shortName)) {
            shortName = shortName.toLowerCase() + "_";
            if (tableName.startsWith(shortName.toLowerCase())) {
                tableName = tableName.substring(shortName.length());
            }
        }
        String entityEnName = JavaUtils.tableNameToJava(tableName); // trans to java
        bo.setEnName(entityEnName);

        // 处理primarykey
        int pstart = sql.indexOf("primary key");
        int pend = sql.indexOf(")", pstart);
        String primaryKeys[] = sql.substring(pstart, pend).split("\\(");
        String primaryKey = primaryKeys[primaryKeys.length - 1];

        // 处理column
        int cstart = sql.indexOf("(") + 2;
        int cend = sql.indexOf("\n", cstart);
        String propString = sql.substring(cstart, cend);
        while (!propString.startsWith(")")) {
            propString = propString.trim();
            PropertyBo property = new PropertyBo();
            bo.addProp(property);
            String[] stepPropStrs = propString.split("\\s+");
            String columnName = stepPropStrs[0];
            if (columnName.equals(primaryKey)) {
                property.setPrimaryKey(true);
            } else {
                property.setPrimaryKey(false);
            }
            String propEnName = JavaUtils.tableNameToJava(columnName); // trans to java
            String dataType = stepPropStrs[1];
            property.setEnName(propEnName);
            String[] dataTypeStrs = dataType.split("\\(|\\)|,");
            String realDataType = dataTypeStrs[0];
            String length = null;
            if (dataTypeStrs.length > 1) {
                length = dataTypeStrs[1];
                property.setLength(length);
            }
            String javaType = DbTypeOracleUtils.getJavaValue(realDataType, length);
            property.setDataType(javaType);
            int index = propString.indexOf("not null");
            if (index != -1) {
                property.setIsNull(false);
            } else {
                property.setIsNull(true);
            }
            index = propString.indexOf("default");
            if (index != -1) {
                index = index + "default".length();
                int indexEnd = propString.indexOf(",");
                String defaultValue = propString.substring(index, indexEnd).trim();
                property.setDefaultValue(defaultValue);
            }

            // 继续获得
            cstart = cend + 1;
            cend = sql.indexOf("\n", cstart);
            propString = sql.substring(cstart, cend);
        }

        // 处理comment
        int index = sql.indexOf("comment on table");
        int indexEnd = sql.indexOf("alter table", index);
        String commentStr = sql.substring(index, indexEnd);
        index = commentStr.indexOf("is '") + "is '".length();
        indexEnd = commentStr.indexOf("';", index);
        String tableComment = commentStr.substring(index, indexEnd);
        bo.setCnName(tableComment);
        String columnComment = null;
        Property property = null;
        int cindex = 0;
        while (true) {
            index = commentStr.indexOf("is '", indexEnd);
            if (index == -1) {
                break;
            }
            index = index + +"is '".length();
            indexEnd = commentStr.indexOf("';", index);
            columnComment = commentStr.substring(index, indexEnd);

            // 获得property 处理
            property = bo.getPropList().get(cindex);
            cindex++;
            property.setCnName(columnComment);
        }
        return bo;
    }

}
