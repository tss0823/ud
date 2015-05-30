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
import com.usefullc.ud.domain.Project;
import com.usefullc.ud.service.IProjectService;
import com.usefullc.ud.web.query.ProjectQuery;

/**
 * 项目 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * 进入项目列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ProjectQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Project> page = projectService.getProjectListPage(queryMap);
        model.addAttribute("page", page);
        return "project/list";
    }

    /**
     * 进入项目新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        return "project/new";
    }

    /**
     * 进入项目修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        Project domain = projectService.getProject(id);
        model.addAttribute("domain", domain);
        return "project/edit";
    }

    /**
     * 进入项目查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        Project domain = projectService.getProject(id);
        model.addAttribute("domain", domain);
        return "project/view";
    }

    /**
     * 项目保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(Project domain) {
        domain.setCreator(getUsername());
        projectService.insertProject(domain);
        return SUCCESS;
    }

    /**
     * 项目修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(Project domain) {
        Project oldDomain = projectService.getProject(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        projectService.updateProject(oldDomain);
        return SUCCESS;
    }

    /**
     * 项目删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        projectService.deleteProject(id);
        return SUCCESS;
    }

    /**
     * 获得项目树
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getTreeList.htm")
    @ResponseBody
    public List<TreeFrm> getTreeList() {
        return projectService.getTreeList();
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
    public List<Project> getSelList() {
        return this.projectService.getProjectList(null);
    }

}
