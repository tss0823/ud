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
import com.usefullc.ud.common.enums.ValidRuleTypeEnum;
import com.usefullc.ud.domain.ValidRule;
import com.usefullc.ud.service.IValidRuleService;
import com.usefullc.ud.web.query.ValidRuleQuery;

/**
 * 校验规则 Controller
 * 
 * @author tangss
 * @2014-03-13 15
 */
@Controller
@RequestMapping(value = "/validRule")
public class ValidRuleController extends BaseController {

    @Autowired
    private IValidRuleService   validRuleService;

    private final static String LIST = "redirect:/validRule/list.htm";

    /**
     * 进入校验规则列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(ValidRuleQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<ValidRule> page = validRuleService.getValidRuleListPage(queryMap);
        model.addAttribute("page", page);
        return "/validRule/list";
    }

    /**
     * 进入校验规则新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        model.addAttribute("validRuleTypeList", ValidRuleTypeEnum.values());
        return "/validRule/new";
    }

    /**
     * 进入校验规则修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        ValidRule domain = validRuleService.getValidRule(id);
        model.addAttribute("domain", domain);
        model.addAttribute("validRuleTypeList", ValidRuleTypeEnum.values());
        return "/validRule/edit";
    }

    /**
     * 进入校验规则查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        ValidRule domain = validRuleService.getValidRule(id);
        model.addAttribute("domain", domain);
        return "/validRule/view";
    }

    /**
     * 校验规则保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(ValidRule domain) {
        validRuleService.insertValidRule(domain);
        return SUCCESS;
    }

    /**
     * 校验规则修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(ValidRule domain) {
        ValidRule oldDomain = validRuleService.getValidRule(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        validRuleService.updateValidRule(oldDomain);
        return SUCCESS;
    }

    /**
     * 校验规则删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        validRuleService.deleteValidRule(id);
        return SUCCESS;
    }

    /**
     * 获得json格式列表
     * 
     * @return
     */
    @RequestMapping(value = "/getJsonList.htm")
    @ResponseBody
    public List<ValidRule> getJsonList() {
        List<ValidRule> list = validRuleService.getValidRuleList(null);
        return list;
    }

}
