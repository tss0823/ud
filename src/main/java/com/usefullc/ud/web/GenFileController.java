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
import com.usefullc.ud.domain.GenFile;
import com.usefullc.ud.service.IGenFileService;
import com.usefullc.ud.web.query.GenFileQuery;

/**
 * 生成文件 Controller
 * 
 * @author tangss
 * @2014-03-27 16
 */
@Controller
@RequestMapping(value = "/genFile")
public class GenFileController extends BaseController {

    @Autowired
    private IGenFileService genFileService;

    /**
     * 进入生成文件树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "/genFile/listTree";
    }

    /**
     * 进入生成文件列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(GenFileQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<GenFile> page = genFileService.getGenFileListPage(queryMap);
        model.addAttribute("page", page);
        model.addAttribute("appId", query.getAppId());
        return "genFile/list";
    }

    /**
     * 进入生成文件新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        return "genFile/new";
    }

    /**
     * 进入生成文件修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        GenFile domain = genFileService.getGenFile(id);
        model.addAttribute("domain", domain);
        return "genFile/edit";
    }

    /**
     * 进入生成文件查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        GenFile domain = genFileService.getGenFile(id);
        model.addAttribute("domain", domain);
        return "genFile/view";
    }

    /**
     * 生成文件保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(GenFile domain) {
        genFileService.insertGenFile(domain);
        return SUCCESS;
    }

    /**
     * 生成文件修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(GenFile domain) {
        GenFile oldDomain = genFileService.getGenFile(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        genFileService.updateGenFile(oldDomain);
        return SUCCESS;
    }

    /**
     * 生成文件删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        genFileService.deleteGenFile(id);
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
        genFileService.download(id, getResponse());
    }
}
