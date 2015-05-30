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
import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IDbConfigureService;
import com.usefullc.ud.web.form.ApplicationFrm;
import com.usefullc.ud.web.form.CommonJsonForm;
import com.usefullc.ud.web.query.ApplicationQuery;

/**
 * 应用 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/application")
public class ApplicationController extends BaseController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IDbConfigureService dbConfigureService;

    /**
     * 进入应用树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "/application/listTree";
    }

    /**
     * 进入应用列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ApplicationQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<ApplicationFrm> page = applicationService.getApplicationFrmListPage(queryMap);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        return "/application/list";
    }

    /**
     * 进入应用新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(@RequestParam Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        List<DbConfigure> dbConfList = dbConfigureService.getDbConfigureList(null);
        model.addAttribute("dbConfList", dbConfList);
        return "/application/new";
    }

    /**
     * 进入应用修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        ApplicationFrm domain = applicationService.getApplicationFrm(id);
        model.addAttribute("domain", domain);
        List<DbConfigure> dbConfList = dbConfigureService.getDbConfigureList(null);
        model.addAttribute("dbConfList", dbConfList);
        return "/application/edit";
    }

    /**
     * 进入应用查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        Application domain = applicationService.getApplication(id);
        model.addAttribute("domain", domain);
        return "/application/view";
    }

    /**
     * 应用保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(Application domain) {
        domain.setCreator(getUsername());
        applicationService.insertApplication(domain);
        return SUCCESS;
    }

    /**
     * 应用修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(Application domain) {
        Application oldDomain = applicationService.getApplication(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        applicationService.updateApplication(oldDomain);
        return SUCCESS;
    }

    /**
     * 应用删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        applicationService.deleteApplication(id);
        return SUCCESS;
    }

    /**
     * 升级
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/upgrade.htm")
    @ResponseBody
    public String upgrade(@RequestParam Long id) {
        applicationService.upgrade(id);
        return SUCCESS;
    }

    /**
     * 构建sql
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/buildSql.htm")
    @ResponseBody
    public CommonJsonForm buildSql(@RequestParam Long id) {
        String sql = applicationService.buildSql(id);
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, sql);
        return frm;
    }

    /**
     * 构建app
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/buildApp.htm")
    @ResponseBody
    public String buildApp(@RequestParam Long id) {
        applicationService.buildApp(id);
        return SUCCESS;
    }

    /**
     * 上传sql
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/uploadSql.htm")
    @ResponseBody
    public String uploadSql(@RequestParam Long id, @RequestParam MultipartFile sqlFile) {
        applicationService.uploadSql(id, sqlFile);
        return SUCCESS;
    }

    /**
     * 获得插入sql
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/getInsertSql.htm")
    @ResponseBody
    public CommonJsonForm getInsertSql(@RequestParam Long id, @RequestParam MultipartFile sqlFile) {
        String sql = applicationService.getInsertSql(id, sqlFile);
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, sql);
        return frm;
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
        return applicationService.getTreeList();
    }

    /**
     * 获得下拉列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getSelList.htm")
    @ResponseBody
    public List<Application> getSelList() {
        return this.applicationService.getApplicationList(null);
    }
}
