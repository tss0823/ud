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
import com.usefullc.ud.domain.ValidItem;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IValidItemPropertyService;
import com.usefullc.ud.service.IValidItemService;
import com.usefullc.ud.web.query.ValidItemQuery;

/**
 * 校验项 Controller
 * 
 * @author tangss
 * @2014-03-13 15
 */
@Controller
@RequestMapping(value = "/validItem")
public class ValidItemController extends BaseController {

    @Autowired
    private IValidItemService         validItemService;

    @Autowired
    private IValidItemPropertyService validItemPropertyService;

    @Autowired
    private IApplicationService       applicationService;

    /**
     * 进入树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "/validItem/listTree";
    }

    /**
     * 进入校验项列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ValidItemQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<ValidItem> page = validItemService.getValidItemListPage(queryMap);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        return "/validItem/list";
    }

    /**
     * 进入校验项新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(@RequestParam Long appId, @RequestParam Long entityId, Model model) {
        model.addAttribute("appId", appId);
        model.addAttribute("entityId", entityId);
        return "/validItem/new";
    }

    /**
     * 进入校验项修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        ValidItem domain = validItemService.getValidItem(id);
        model.addAttribute("domain", domain);
        return "/validItem/edit";
    }

    /**
     * 进入校验项查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        ValidItem domain = validItemService.getValidItem(id);
        model.addAttribute("domain", domain);
        return "/validItem/view";
    }

    /**
     * 校验项保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(ValidItem domain, Long[] propIds) {
        validItemService.insertValidItem(domain, propIds);
        return SUCCESS;
    }

    /**
     * 校验项修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(ValidItem domain, Long[] propIds) {
        ValidItem oldDomain = validItemService.getValidItem(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        validItemService.updateValidItem(oldDomain, propIds);
        return SUCCESS;
    }

    /**
     * 校验项删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        validItemService.deleteValidItem(id);
        return SUCCESS;
    }

    /**
     * 同步校验文件
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/syncValidFile.htm")
    @ResponseBody
    public String syncValidFile(@RequestParam Long appId, @RequestParam String ids) {
        applicationService.buildValidApp(appId, ids.split(","));
        return SUCCESS;
    }

    /**
     * 获得属性树
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getPropItemTreeList.htm")
    @ResponseBody
    public List<TreeFrm> getPropItemTreeList(@RequestParam Long validItemId) {
        return validItemPropertyService.getTreeList(validItemId);
    }

}
