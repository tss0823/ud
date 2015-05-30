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

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.ud.domain.EnumData;
import com.usefullc.ud.domain.EnumDataItem;
import com.usefullc.ud.service.IEnumDataItemService;
import com.usefullc.ud.service.IEnumDataService;
import com.usefullc.ud.web.query.EnumDataItemQuery;

/**
 * 枚举数据项 Controller
 * 
 * @author ${author}
 * @2013-10-12 13
 */
@Controller
@RequestMapping(value = "/enumDataItem")
public class EnumDataItemController extends BaseController {

    @Autowired
    private IEnumDataItemService enumDataItemService;

    @Autowired
    private IEnumDataService     enumDataService;

    /**
     * 进入枚举数据项列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(EnumDataItemQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<EnumDataItem> list = enumDataItemService.getEnumDataItemList(queryMap);
        model.addAttribute("list", list);
        model.addAttribute("parentId", query.getParentId());
        return "/enumDataItem/list";
    }

    /**
     * 进入枚举数据项详情
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/listDetail.htm")
    public String listDetail(EnumDataItemQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<EnumDataItem> list = enumDataItemService.getEnumDataItemList(queryMap);
        model.addAttribute("list", list);
        EnumData enumData = enumDataService.getEnumData(query.getParentId());
        model.addAttribute("enumData", enumData);
        return "/enumDataItem/detail";
    }

    /**
     * 枚举数据项删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    public void delete(@RequestParam Long id) {
        try {
            enumDataItemService.deleteEnumDataItem(id);
            this.write("1");
        } catch (Exception e) {
            this.write("0");
        }
    }

    /**
     * 枚举数据项保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    public String save(@RequestParam Long parentId, @RequestParam String[] value, @RequestParam String[] text,
                       @RequestParam String[] extText) {
        enumDataItemService.updateEnumDataItem(parentId, value, text, extText);
        return redirect("enumDataItem/list.htm") + "?parentId=" + parentId;
    }

}
