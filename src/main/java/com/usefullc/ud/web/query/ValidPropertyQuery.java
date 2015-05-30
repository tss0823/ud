/**
 * 
 */
package com.usefullc.ud.web.query;

import com.usefullc.platform.common.web.BaseQuery;

/**
 * 校验属性
 * 
 * @author tangss
 * @2014-03-13 16
 */
public class ValidPropertyQuery extends BaseQuery {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** * 删除状态 */
    private Integer           delState;

    /**
     * 实体id
     */
    private Long              entityId;

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

}
