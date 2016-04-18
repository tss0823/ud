/**
 * 
 */
package com.usefullc.ud.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;
import com.usefullc.ud.common.db.DbBuilder;
import com.usefullc.ud.common.db.DbBuilderFactory;

/**
 * @author tangss
 * @2013年8月30日 @下午2:34:49
 */
public class TestDbMysqlBuilder {

    public void buildTableSqlTest() {
        EntityBo entity = new EntityBo();
        entity.setCnName("测试");
        entity.setEnName("test");

        List<PropertyBo> list = new ArrayList<PropertyBo>();
        PropertyBo prop = new PropertyBo();
        prop.setCnName("ID");
        prop.setEnName("id");
        prop.setDataType("java.lang.Long");
        prop.setLength("20");
        // prop.setDefaultValue(null);
        prop.setIsNull(false);
        prop.setPrimaryKey(true);
        list.add(prop);
        prop = new PropertyBo();
        prop.setCnName("文本");
        prop.setEnName("text");
        prop.setDataType("java.lang.String");
        prop.setLength("20");
        // prop.setDefaultValue(null);
        prop.setIsNull(true);
        prop.setPrimaryKey(false);
        list.add(prop);
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder("mysql");
        // String sql = dbBuilder.buildTableSql(entity, list);
        // System.out.println(sql);
    }

    public EntityBo parseSqlToBoTest() {
        File file = new File("d:/test.sql");
        String sql = null;
        try {
            sql = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder("mysql");
        // EntityBo bo = dbBuilder.parseSqlToBo(sql);
        // System.out.println(bo);
        return null;
    }

    public static void main(String[] args) {
        TestDbMysqlBuilder test = new TestDbMysqlBuilder();
        // test.parseSqlToBoTest();
        test.buildTableSqlTest();
    }
}
