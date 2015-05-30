/**
 * 
 */
package com.usefullc.ud.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.HisProperty;

/**
 * @author tangss
 * @2013年8月30日 @下午2:18:56
 */
public class EntityBo extends Entity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** * 首字母大写英文名 */
    private String            upperEntityEnName;

    /** * 表名 */
    private String            tableName;

    /** * 属性集合 */
    private List<PropertyBo>  propList         = new ArrayList<PropertyBo>();

    /**
     * 上一版本属性集合
     */
    private List<HisProperty> preHisPropList   = new ArrayList<HisProperty>();

    public List<PropertyBo> getPropList() {
        return propList;
    }

    public void setPropList(List<PropertyBo> propList) {
        this.propList = propList;
    }

    public void addProp(PropertyBo prop) {
        this.propList.add(prop);
    }

    public String getUpperEntityEnName() {
        return upperEntityEnName;
    }

    public void setUpperEntityEnName(String upperEntityEnName) {
        this.upperEntityEnName = upperEntityEnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<HisProperty> getPreHisPropList() {
        return preHisPropList;
    }

    public void setPreHisPropList(List<HisProperty> preHisPropList) {
        this.preHisPropList = preHisPropList;
    }

}
