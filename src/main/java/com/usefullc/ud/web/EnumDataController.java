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

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.EnumData;
import com.usefullc.ud.service.IEnumDataService;
import com.usefullc.ud.web.query.EnumDataQuery;

/**
 * 枚举数据 Controller
 * 
 * @author ${author}
 * @2013-10-12 13
 */
@Controller
@RequestMapping(value = "/enumData")
public class EnumDataController extends BaseController {

    @Autowired
    private IEnumDataService enumDataService;

    /**
     * 进入枚举数据列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(EnumDataQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<EnumData> page = enumDataService.getEnumDataListPage(queryMap);
        model.addAttribute("page", page);
        return "/enumData/list";
    }

    /**
     * 进入枚举数据新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model) {
        return "/enumData/new";
    }

    /**
     * 进入枚举数据修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id, Model model) {
        EnumData domain = enumDataService.getEnumData(id);
        model.addAttribute("domain", domain);
        return "/enumData/edit";
    }

    /**
     * 进入枚举数据查看
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id, Model model) {
        EnumData domain = enumDataService.getEnumData(id);
        model.addAttribute("domain", domain);
        return "/enumData/view";
    }

    /**
     * 枚举数据保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    public String save(EnumData domain) {
        enumDataService.insertEnumData(domain);
        return redirect("enumData/list.htm");
    }

    /**
     * 枚举数据修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
    public String update(EnumData domain) {
        EnumData oldDomain = enumDataService.getEnumData(domain.getId());
        BeanUtils.beanCopy(domain, oldDomain);
        enumDataService.updateEnumData(oldDomain);
        return redirect("enumData/list.htm");
    }

    /**
     * 枚举数据删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    public String delete(@RequestParam Long id) {
        enumDataService.deleteEnumData(id);
        return redirect("enumData/list.htm");
    }

}
