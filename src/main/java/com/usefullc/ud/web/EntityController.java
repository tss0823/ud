/**
 * 
 */
package com.usefullc.ud.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.web.form.CommonJsonForm;
import com.usefullc.ud.web.query.EntityQuery;

/**
 * 实体 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/entity")
public class EntityController extends BaseController {

    @Autowired
    private IEntityService      entityService;

    @Autowired
    private IApplicationService applicationService;

    /**
     * 进入实体树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "/entity/listTree";
    }

    /**
     * 进入实体列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(EntityQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Entity> page = entityService.getEntityListPage(queryMap);
        model.addAttribute("page", page);
        model.addAttribute("appId", query.getAppId());
        return "/entity/list";
    }

    /**
     * 进入实体新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(@RequestParam Long appId, Model model) {
        model.addAttribute("appId", appId);
        return "/entity/new";
    }

    /**
     * 进入实体修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        Entity domain = entityService.getEntity(id);
        model.addAttribute("domain", domain);
        return "/entity/edit";
    }

    /**
     * 进入实体查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        Entity domain = entityService.getEntity(id);
        model.addAttribute("domain", domain);
        return "/entity/view";
    }

    /**
     * 实体保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(Entity domain) {
        entityService.insertEntity(domain);
        return SUCCESS;
    }

    /**
     * 实体修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(Entity domain) {
        entityService.updateEntity(domain);
        return SUCCESS;
    }

    /**
     * 实体删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        entityService.deleteEntity(id);
        return SUCCESS;
    }

    /**
     * 实体删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteAll.htm")
    @ResponseBody
    public String deleteAll(@RequestParam String ids) {
        entityService.deleteEntityByIds(ids.split(","));
        return SUCCESS;
    }

    /**
     * 同步应用
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/syncApp.htm")
    @ResponseBody
    public String syncApp(@RequestParam Long appId, @RequestParam String ids) {
        applicationService.buildSyncApp(appId, ids.split(","));
        return SUCCESS;
    }

    /**
     * 同步sql
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/syncSql.htm")
    @ResponseBody
    public CommonJsonForm syncSql(@RequestParam Long appId, @RequestParam String ids) {
        String str = applicationService.syncSql(appId, ids.split(","));
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, str);
        return frm;
    }

    /**
     * 构建单个应用
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/buildApp.htm")
    @ResponseBody
    public String buildApp(@RequestParam Long appId, @RequestParam String ids) {
        applicationService.buildApp(appId, ids.split(","));
        return SUCCESS;
    }

    /**
     * 构建单个sql
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/buildSql.htm")
    @ResponseBody
    public CommonJsonForm buildSql(@RequestParam Long appId, @RequestParam String ids) {
        String str = applicationService.buildSql(appId, ids.split(","));
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, str);
        return frm;
    }


    /**
     * 构建单个删除sql
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/buildDelSql.htm")
    @ResponseBody
    public CommonJsonForm buildDelSql(@RequestParam Long appId, @RequestParam String ids) {
        String str = applicationService.buildDelSql(appId, ids.split(","));
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, str);
        return frm;
    }

    /**
     * 构建实体配置
     *
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/buildBeanConfig.htm")
    @ResponseBody
    public CommonJsonForm buildBeanConfig(@RequestParam Long appId, @RequestParam String ids) {
        String str = applicationService.buildBeanConfig(appId, ids.split(","));
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, str);
        return frm;
    }
    /**
     * 同步校验
     * 
     * @param appId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/syncValid.htm")
    @ResponseBody
    public String syncValid(@RequestParam Long appId, @RequestParam String ids) {
        applicationService.syncValid(appId, ids.split(","));
        return SUCCESS;
    }

    /**
     * 实体复制
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/copy.htm")
    @ResponseBody
    public String copy(@RequestParam Long id) {
        entityService.copy(id);
        return SUCCESS;
    }

    /**
     * 获得应用树
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getTreeList.htm")
    @ResponseBody
    public List<TreeFrm> getTreeList() {
        return entityService.getTreeList();
    }

    /**
     * 根据应用id获得列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getEntityListByAppId.htm")
    @ResponseBody
    public List<Entity> getEntityListByAppId(@RequestParam Long appId) {
        return entityService.getEntityListByAppId(appId);
    }
}
