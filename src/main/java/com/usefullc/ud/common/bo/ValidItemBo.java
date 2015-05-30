/**
 * 
 */
package com.usefullc.ud.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.usefullc.ud.domain.ValidItem;

/**
 * @author tangss
 * @2013年8月30日 @下午2:18:56
 */
public class ValidItemBo extends ValidItem {

    /**
	 * 
	 */
    private static final long     serialVersionUID = 1L;

    /** * 首字母大写英文名 */
    private String                upperEntityEnName;

    /** * 表名 */
    private String                tableName;

    /** * 属性集合 */
    private List<ValidPropertyBo> propList         = new ArrayList<ValidPropertyBo>();

    public List<ValidPropertyBo> getPropList() {
        return propList;
    }

    public void setPropList(List<ValidPropertyBo> propList) {
        this.propList = propList;
    }

    public void addProp(ValidPropertyBo prop) {
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

}
