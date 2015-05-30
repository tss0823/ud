/**
 * 
 */
package com.usefullc.ud.web.query;

import com.usefullc.platform.common.web.BaseQuery;

/**
 * @author tangss
 * @2013年9月13日 @下午3:34:41
 */
public class EntityQuery extends BaseQuery {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    private Long              projectId;

    /**
     * 应用ID
     */
    private Long              appId;

    private String            enNameLike;

    private String            cnNameLike;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getEnNameLike() {
        return enNameLike;
    }

    public void setEnNameLike(String enNameLike) {
        this.enNameLike = enNameLike;
    }

    public String getCnNameLike() {
        return cnNameLike;
    }

    public void setCnNameLike(String cnNameLike) {
        this.cnNameLike = cnNameLike;
    }

}
