/**
 * 
 */
package com.usefullc.ud.web.query;

import com.usefullc.platform.common.web.BaseQuery;

/**
 * @author tangss
 * @2013年9月13日 @下午3:34:41
 */
public class ApplicationQuery extends BaseQuery {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    private Long              projectId;

    /**
     * 英文名
     */
    private String            enName;

    /**
     * 中文名
     */
    private String            cnName;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

}
