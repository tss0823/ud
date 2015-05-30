/**
 * 
 */
package com.usefullc.ud.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.usefullc.ud.domain.AppData;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.ud.domain.HisEntity;
import com.usefullc.ud.domain.ValidRule;

/**
 * @author tangss
 * @2013年8月31日 @下午4:58:27
 */
public class ApplicationBo extends Application {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String            packagePath;

    private DbConfigure       dbConfigure;

    private TemplateBo        templateBo;

    private AppData           appData;

    private List<EntityBo>    entityBoList     = new ArrayList<EntityBo>();

    private List<ValidItemBo> validItemBoList  = new ArrayList<ValidItemBo>();

    /**
     * 校验规则集合
     */
    private List<ValidRule>   validRuleList    = new ArrayList<ValidRule>();

    // 前一版本历史实体记录
    private List<HisEntity>   preHisEntityList = new ArrayList<HisEntity>();

    public List<EntityBo> getEntityBoList() {
        return entityBoList;
    }

    public void setEntityBoList(List<EntityBo> entityBoList) {
        this.entityBoList = entityBoList;
    }

    public void addEntityBo(EntityBo entityBo) {
        this.entityBoList.add(entityBo);
    }

    public List<HisEntity> getPreHisEntityList() {
        return preHisEntityList;
    }

    public void setPreHisEntityList(List<HisEntity> preHisEntityList) {
        this.preHisEntityList = preHisEntityList;
    }

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public DbConfigure getDbConfigure() {
        return dbConfigure;
    }

    public void setDbConfigure(DbConfigure dbConfigure) {
        this.dbConfigure = dbConfigure;
    }

    public List<ValidItemBo> getValidItemBoList() {
        return validItemBoList;
    }

    public void setValidItemBoList(List<ValidItemBo> validItemBoList) {
        this.validItemBoList = validItemBoList;
    }

    public void addValidItemBo(ValidItemBo validItemBo) {
        this.validItemBoList.add(validItemBo);
    }

    public List<ValidRule> getValidRuleList() {
        return validRuleList;
    }

    public void setValidRuleList(List<ValidRule> validRuleList) {
        this.validRuleList = validRuleList;
    }

    public TemplateBo getTemplateBo() {
        return templateBo;
    }

    public void setTemplateBo(TemplateBo templateBo) {
        this.templateBo = templateBo;
    }

}
