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
import com.usefullc.ud.common.enums.MysqlDataTypeEnum;
import com.usefullc.ud.common.utils.DbTypeMysqlUtils;
import com.usefullc.ud.common.utils.DbUtils;
import com.usefullc.ud.common.utils.JavaUtils;
import com.usefullc.ud.domain.HisProperty;
import com.usefullc.ud.domain.Property;

/**
 * @author tangss
 * @2013年8月30日 @上午11:23:31
 */
public class DbMysqlBuilder implements DbBuilder {

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
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS `");
        String shortName = appBo.getShortName();
        if (!StringUtils.isEmpty(entity.getShortName())) {
            shortName = entity.getShortName();
        }
        String tableName = DbUtils.javaToTableName(entity.getEnName());
        if (!StringUtils.isEmpty(shortName)) {
            tableName = shortName + "_" + tableName;
        }
        sb.append(tableName);
        sb.append("`;");
        sb.append("\n");
        sb.append("CREATE TABLE `");

        sb.append(tableName);
        sb.append("` (");
        String primaryKey = null;
        for (Property prop : propList) {
            sb.append("\n  ");
            sb.append("`");
            String columnName = DbUtils.javaToTableName(prop.getEnName());
            sb.append(columnName);
            sb.append("`");
            sb.append(" ");
            String columnType = DbTypeMysqlUtils.getValue(prop.getDataType());
            sb.append(columnType);
            if (!columnType.equals(MysqlDataTypeEnum.TIME.getDbValue())) {
                sb.append("(");
                if (StringUtils.isEmpty(prop.getLength()) && columnType.equals("tinyint")) {
                    sb.append(4);
                } else {
                    sb.append(prop.getLength());
                }
                sb.append(")");
            }
            sb.append(" ");
            if (prop.getIsNull() != null && !prop.getIsNull()) {
                sb.append("NOT NULL");
                sb.append(" ");
            } else {
                sb.append("DEFAULT");
                sb.append(" ");
                if (StringUtils.isEmpty(prop.getDefaultValue())) {
                    sb.append("NULL");
                } else {
                    sb.append(prop.getDefaultValue());
                }
            }
            if (prop.getPrimaryKey()) {
                sb.append("AUTO_INCREMENT");
                primaryKey = columnName;
            }
            sb.append(" ");
            sb.append("COMMENT");
            sb.append(" ");
            sb.append("'");
            sb.append(prop.getCnName());
            sb.append("'");
            sb.append(",");
        }
        sb.append("\n");
        sb.append("  PRIMARY KEY (`");
        sb.append(primaryKey);
        sb.append("`)");
        sb.append("\n");
        sb.append(")");
        sb.append(" ");
        sb.append("ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='");
        sb.append(entity.getCnName());
        sb.append("';");
        sb.append("\n\n");
        sql = sb.toString();
        return sql;
    }

    @Override
    public String buildTableAlertSql(String shortName, EntityBo entityBo) {
        StringBuilder sb = new StringBuilder("ALTER TABLE `");
        if (!StringUtils.isEmpty(entityBo.getShortName())) {
            shortName = entityBo.getShortName();
        }
        String tableName = DbUtils.javaToTableName(entityBo.getEnName());
        if (!StringUtils.isEmpty(shortName)) {
            tableName = shortName + "_" + tableName;
        }
        sb.append(tableName);
        sb.append("`");
        sb.append("\n  ");
        List<PropertyBo> propBoList = entityBo.getPropList();
        List<HisProperty> preHisPropList = entityBo.getPreHisPropList();
        for (Iterator<PropertyBo> it = propBoList.iterator(); it.hasNext();) {
            PropertyBo propBo = it.next();
            for (Iterator<HisProperty> iter = preHisPropList.iterator(); iter.hasNext();) {
                HisProperty hisProp = iter.next();
                if (propBo.equals(hisProp)) {
                    it.remove();
                    iter.remove();
                }
            }
        }
        int propSize = propBoList.size();
        int hisSize = preHisPropList.size();
        if (propSize == 0 && hisSize == 0) {
            return "";
        }

        int size = propSize >= hisSize ? hisSize : propSize;
        for (int i = 0; i < size; i++) {
            PropertyBo propBo = propBoList.get(i);
            HisProperty hisProp = preHisPropList.get(i);
            String columnName = DbUtils.javaToTableName(hisProp.getEnName());
            sb.append("CHANGE COLUMN `" + columnName + "` ");
            columnName = DbUtils.javaToTableName(propBo.getEnName());
            sb.append("`" + columnName + "`");
            coloumnAppend(sb, propBo);
            sb.append(",\n");

        }
        if (propSize >= hisSize) { // add
            for (int i = size; i < propSize; i++) {
                PropertyBo propBo = propBoList.get(i);
                String columnName = DbUtils.javaToTableName(propBo.getEnName());
                sb.append(" ADD COLUMN  `" + columnName + "` ");
                coloumnAppend(sb, propBo);
                sb.append(",\n");
            }

        } else { // remove
            for (int i = size; i < hisSize; i++) {
                HisProperty hisProp = preHisPropList.get(i);
                String columnName = DbUtils.javaToTableName(hisProp.getEnName());
                sb.append(" DROP COLUMN  `" + columnName + "` ");
                sb.append(",\n");
            }
        }
        if (sb.indexOf(",\n") != -1) {
            return sb.substring(0, sb.length() - 2) + ";\n";
        } else {
            return "";
        }
    }

    private static void coloumnAppend(StringBuilder sb, PropertyBo propBo) {
        String columnType = DbTypeMysqlUtils.getValue(propBo.getDataType());
        sb.append(columnType);
        if (!columnType.equals(MysqlDataTypeEnum.TIME.getDbValue())) {
            sb.append("(");
            if (StringUtils.isEmpty(propBo.getLength()) && columnType.equals("tinyint")) {
                sb.append(4);
            } else {
                sb.append(propBo.getLength());
            }
            sb.append(")");
        }
        sb.append(" ");
        if (propBo.getIsNull() != null && !propBo.getIsNull()) {
            sb.append("NOT NULL");
            sb.append(" ");
        } else {
            sb.append("DEFAULT");
            sb.append(" ");
            if (StringUtils.isEmpty(propBo.getDefaultValue())) {
                sb.append("NULL");
            } else {
                sb.append(propBo.getDefaultValue());
            }
        }
        if (propBo.getPrimaryKey()) {
            sb.append("AUTO_INCREMENT");
        }
        sb.append(" ");
        sb.append("COMMENT");
        sb.append(" ");
        sb.append("'");
        sb.append(propBo.getCnName());
        sb.append("'");
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
                if (!start && !line.startsWith("DROP")) {
                    continue;
                } else if (line.startsWith("DROP")) {
                    start = true;
                } else if (start && line.startsWith(") ")) {
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

        int n = sql.indexOf("(");
        // table 部分
        String tableStr = sql.substring(0, n);
        String tableName = tableStr.split("`")[1];
        if (!StringUtils.isEmpty(shortName)) {
            shortName = shortName.toLowerCase() + "_";
            if (tableName.startsWith(shortName.toLowerCase())) {
                tableName = tableName.substring(shortName.length());
            }
        }
        String entityEnName = JavaUtils.tableNameToJava(tableName); // trans to java
        bo.setEnName(entityEnName);

        // 中间属性部分
        int end = sql.lastIndexOf(")");
        String propStr = sql.substring(n + 1, end).trim();
        String propStrs[] = propStr.split("\n");
        for (String propString : propStrs) {
            propString = propString.trim();
            if (propString.indexOf("PRIMARY KEY") != -1) {
                continue;
            }
            PropertyBo property = new PropertyBo();
            bo.addProp(property);
            String[] stepPropStrs = propString.split("\\s+");
            String propEnName = stepPropStrs[0];
            propEnName = propEnName.split("`")[1];
            propEnName = JavaUtils.tableNameToJava(propEnName); // trans to java
            String dataType = stepPropStrs[1];
            property.setEnName(propEnName);
            String[] dataTypeStrs = dataType.split("\\(|\\)");
            String realDataType = dataTypeStrs[0];
            String javaType = DbTypeMysqlUtils.getValue(realDataType);
            property.setDataType(javaType);
            if (dataTypeStrs.length > 1) {
                String length = dataTypeStrs[1];
                property.setLength(length);
            }
            int index = propString.indexOf("NOT NULL");
            if (index != -1) {
                property.setIsNull(false);
            } else {
                property.setIsNull(true);
            }
            index = propString.indexOf("AUTO_INCREMENT");
            if (index != -1) {
                property.setPrimaryKey(true);
            } else {
                property.setPrimaryKey(false);
            }
            index = propString.indexOf("DEFAULT");
            if (index != -1) {
                index = index + "DEFAULT".length();
                int indexEnd = propString.indexOf("COMMENT");
                if (indexEnd == -1) { // no comment
                    indexEnd = propString.indexOf(",");
                }
                String defaultValue = propString.substring(index, indexEnd).trim();
                property.setDefaultValue(defaultValue);
            }
            index = propString.indexOf("COMMENT");
            if (index != -1) {
                index = index + "COMMENT".length();
                String propCnName = propString.substring(index);
                propCnName = propCnName.split("'")[1];
                property.setCnName(propCnName);
            }
        }
        // 结尾部分
        String endStr = sql.substring(end);
        String entityCnName = endStr.split("'")[1];
        bo.setCnName(entityCnName);
        return bo;
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.common.db.DbBuilder#buildDelTableSql(com.usefullc.ud.common.bo.ApplicationBo, java.util.List)
     */
    @Override
    public String buildDelTableSql(ApplicationBo appBo, List<EntityBo> entityList) {
        // TODO Auto-generated method stub
        return null;
    }

}
