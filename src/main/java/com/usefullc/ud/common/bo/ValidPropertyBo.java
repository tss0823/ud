/**
 * 
 */
package com.usefullc.ud.common.bo;

import java.util.List;

import com.usefullc.ud.domain.ValidProperty;

/**
 * @author tangss
 * @2013年9月2日 @上午9:40:15
 */
public class ValidPropertyBo extends ValidProperty {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String            columnType;

    private String            upperEnName;

    private String            columnName;

    private String            upperColumnName;

    /**
     * java校验集合数据
     */
    private List<String>      validList;

    /**
     * js校验集合数据
     */
    private List<String>      jsRuleValidList;
    private List<String>      jsMsgValidList;

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getUpperEnName() {
        return upperEnName;
    }

    public void setUpperEnName(String upperEnName) {
        this.upperEnName = upperEnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getUpperColumnName() {
        return upperColumnName;
    }

    public void setUpperColumnName(String upperColumnName) {
        this.upperColumnName = upperColumnName;
    }

    public List<String> getValidList() {
        return validList;
    }

    public void setValidList(List<String> validList) {
        this.validList = validList;
    }

    public List<String> getJsRuleValidList() {
        return jsRuleValidList;
    }

    public void setJsRuleValidList(List<String> jsRuleValidList) {
        this.jsRuleValidList = jsRuleValidList;
    }

    public List<String> getJsMsgValidList() {
        return jsMsgValidList;
    }

    public void setJsMsgValidList(List<String> jsMsgValidList) {
        this.jsMsgValidList = jsMsgValidList;
    }

}
