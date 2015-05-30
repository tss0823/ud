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
import com.usefullc.ud.domain.ValidProperty;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IPropertyService;
import com.usefullc.ud.service.IValidPropertyService;
import com.usefullc.ud.web.query.ValidPropertyQuery;

/**
 * 校验属性 Controller
 * 
 * @author tangss
 * @2014-03-13 15
 */
@Controller
@RequestMapping(value = "/valid")
public class ValidPropertyController extends BaseController {

    @Autowired
    private IValidPropertyService validPropertyService;

    @Autowired
    private IPropertyService      propertyService;

    @Autowired
    private IEntityService        entityService;

    /**
     * 进入实体树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "/valid/listTree";
    }

    /**
     * 进入校验属性列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ValidPropertyQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<ValidProperty> page = validPropertyService.getValidPropertyListPage(queryMap);
        model.addAttribute("page", page);
        return "/valid/list";
    }

    /**
     * 进入校验属性修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        ValidProperty domain = validPropertyService.getValidProperty(id);
        model.addAttribute("domain", domain);
        return "/valid/edit";
    }

    /**
     * 进入校验属性查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        ValidProperty domain = validPropertyService.getValidProperty(id);
        model.addAttribute("domain", domain);
        return "/valid/view";
    }

    /**
     * 校验属性修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate.htm")
    @ResponseBody
    public String saveOrUpdate(@RequestParam Long[] ids,

    @RequestParam String[] msgName,

    @RequestParam Long[] ruleId,

    @RequestParam String[] ruleParam,

    @RequestParam String[] msg) {
        validPropertyService.saveOrupdateProperty(ids, msgName, ruleId, ruleParam, msg);
        return SUCCESS;
    }

    /**
     * 校验属性删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        validPropertyService.deleteValidProperty(id);
        return SUCCESS;
    }

    /**
     * 获得属性树
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/getTreeList.htm")
    @ResponseBody
    public List<TreeFrm> getTreeList() {
        return validPropertyService.getTreeList();
    }

}
