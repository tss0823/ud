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
import com.usefullc.ud.common.enums.TemplateTypeEnum;
import com.usefullc.ud.domain.Template;
import com.usefullc.ud.service.ITemplateService;
import com.usefullc.ud.web.query.TemplateQuery;

/**
 * 模版 Controller
 * 
 * @author tangss
 * @2014-03-27 16
 */
@Controller
@RequestMapping(value = "/template")
public class TemplateController extends BaseController {

    @Autowired
    private ITemplateService templateService;

    /**
     * 选择列表窗口
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/select.htm")
    public String selectParent(String id, String name, Model model) {
        List<Template> list = templateService.getTemplateList(null);
        model.addAttribute("list", list);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "template/select";
    }

    /**
     * 进入模版列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(TemplateQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Template> page = templateService.getTemplateListPage(queryMap);
        model.addAttribute("page", page);
        return "template/list";
    }

    /**
     * 进入模版新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        model.addAttribute("typeList", TemplateTypeEnum.values());
        return "template/new";
    }

    /**
     * 进入模版修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        Template domain = templateService.getTemplate(id);
        model.addAttribute("domain", domain);
        model.addAttribute("typeList", TemplateTypeEnum.values());
        return "template/edit";
    }

    /**
     * 进入模版查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        Template domain = templateService.getTemplate(id);
        model.addAttribute("domain", domain);
        return "template/view";
    }

    /**
     * 模版保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(Template domain, @RequestParam MultipartFile tplFile) {
        templateService.insertTemplate(domain, tplFile);
        return SUCCESS;
    }

    /**
     * 模版修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(Template domain, @RequestParam MultipartFile tplFile) {
        Template oldDomain = templateService.getTemplate(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        templateService.updateTemplate(oldDomain, tplFile);
        return SUCCESS;
    }

    /**
     * 模版删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        templateService.deleteTemplate(id);
        return SUCCESS;
    }

    /**
     * 生成文件下载
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/download.htm")
    @ResponseBody
    public void download(@RequestParam Long id) {
        templateService.download(id, getResponse());
    }

}
