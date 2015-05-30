/**
 * 
 */
package com.usefullc.ud.common;

import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;

/**
 * @author tangss
 * @2013年9月2日 @上午10:39:26
 */
public class TestApplicationBuilder {

    public static void main(String[] args) {
        // ApplicationBuilder ab = new ApplicationBuilder();
        // ApplicationBo appBo = getAppBo();
        // DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(appBo.getDbConfigure().getType());
        // List<EntityBo> entityList = appBo.getEntityBoList();
        // StringBuilder sb = new StringBuilder();
        // for (EntityBo entityBo : entityList) {
        // String str = dbBuilder.buildTableSql(entityBo, entityBo.getPropList());
        // sb.append(str);
        // }
        // System.out.println(sb);
        // ab.buildApp(appBo);
    }

    private static ApplicationBo getAppBo() {
        ApplicationBo appBo = new ApplicationBo();
        appBo.setEnName("shediao");
        appBo.setTemplatePath("D:\\workspace\\ud\\src\\main\\webapp\\template\\default\\");
        appBo.setGenPath("d:/shediao/");
        appBo.setPackageName("com.test.abc");

        EntityBo entityBo = new EntityBo();
        appBo.addEntityBo(entityBo);
        entityBo.setCnName("郭靖");
        entityBo.setEnName("guojing");

        PropertyBo propertyBo = new PropertyBo();
        propertyBo.setCnName("ID");
        propertyBo.setEnName("id");
        propertyBo.setDataType("java.lang.Long");
        entityBo.addProp(propertyBo);

        propertyBo = new PropertyBo();
        propertyBo.setCnName("爱好");
        propertyBo.setEnName("love");
        propertyBo.setDataType("java.lang.String");
        entityBo.addProp(propertyBo);

        propertyBo = new PropertyBo();
        propertyBo.setCnName("身高");
        propertyBo.setEnName("len");
        propertyBo.setDataType("java.lang.Integer");
        entityBo.addProp(propertyBo);

        entityBo = new EntityBo();
        appBo.addEntityBo(entityBo);
        entityBo.setCnName("黄蓉");
        entityBo.setEnName("huangrong");

        propertyBo = new PropertyBo();
        propertyBo.setCnName("ID");
        propertyBo.setEnName("id");
        propertyBo.setDataType("java.lang.Long");
        entityBo.addProp(propertyBo);

        propertyBo = new PropertyBo();
        propertyBo.setCnName("性格");
        propertyBo.setEnName("nature");
        propertyBo.setDataType("java.lang.String");
        entityBo.addProp(propertyBo);

        propertyBo = new PropertyBo();
        propertyBo.setCnName("身高");
        propertyBo.setEnName("len");
        propertyBo.setDataType("java.lang.Integer");
        entityBo.addProp(propertyBo);
        return appBo;
    }

}
