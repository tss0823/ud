/**
 * 
 */
package com.usefullc.ud.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.common.enums.DbTypeEnum;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.ud.service.IDbConfigureService;
import com.usefullc.ud.web.query.DbConfigureQuery;

/**
 * 数据库配置 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/dbConfigure")
public class DbConfigureController extends BaseController {

    @Autowired
    private IDbConfigureService dbConfigureService;

    /**
     * 进入数据库配置列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(DbConfigureQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<DbConfigure> page = dbConfigureService.getDbConfigureListPage(queryMap);
        model.addAttribute("page", page);
        return "/dbConfigure/list";
    }

    /**
     * 进入数据库配置新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        model.addAttribute("dbTypeList", DbTypeEnum.values());
        return "/dbConfigure/new";
    }

    /**
     * 进入数据库配置修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        DbConfigure domain = dbConfigureService.getDbConfigure(id);
        model.addAttribute("domain", domain);
        model.addAttribute("dbTypeList", DbTypeEnum.values());
        return "/dbConfigure/edit";
    }

    /**
     * 进入数据库配置查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        DbConfigure domain = dbConfigureService.getDbConfigure(id);
        model.addAttribute("domain", domain);
        return "/dbConfigure/view";
    }

    /**
     * 数据库配置保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(DbConfigure domain) {
        dbConfigureService.insertDbConfigure(domain);
        return SUCCESS;
    }

    /**
     * 数据库配置修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(DbConfigure domain) {
        DbConfigure oldDomain = dbConfigureService.getDbConfigure(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        dbConfigureService.updateDbConfigure(oldDomain);
        return SUCCESS;
    }

    /**
     * 数据库配置删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        dbConfigureService.deleteDbConfigure(id);
        return SUCCESS;
    }

}
