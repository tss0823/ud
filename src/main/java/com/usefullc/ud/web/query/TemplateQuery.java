/**
 * 
 */
package com.usefullc.ud.web.query;

import com.usefullc.platform.common.web.BaseQuery;

/**
 * 模版
 * 
 * @author tangss
 * @2014-03-27 16
 */
public class TemplateQuery extends BaseQuery {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** * 删除状态 */
    private Integer           delState;

    /**
     * 项目ID
     */
    private Long              projectId;

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
