/**
 * 
 */
package com.usefullc.ud.web.query;

import com.usefullc.platform.common.web.BaseQuery;

/**
 * 枚举数据项
 * 
 * @author ${author}
 * @2013-10-12 13
 */
public class EnumDataItemQuery extends BaseQuery {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** * 删除状态 */
    private Integer           delState;

    private Long              parentId;

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
