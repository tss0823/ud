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
import com.usefullc.ud.common.enums.DataTypeEnum;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IPropertyService;
import com.usefullc.ud.web.query.PropertyQuery;

/**
 * 属性 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/property")
public class PropertyController extends BaseController {

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private IEntityService   entityService;

    /**
     * 进入实体树列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTree.htm")
    public String listTree() {
        return "property/listTree";
    }

    /**
     * 进入属性列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(PropertyQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<Property> list = propertyService.getPropertyList(queryMap);
        model.addAttribute("list", list);
        model.addAttribute("entityId", query.getEntityId());
        model.addAttribute("dataTypeEnums", DataTypeEnum.values());
        return "property/list";
    }

    @RequestMapping(value = "/listDetail.htm")
    public String listDetail(PropertyQuery query, Model model) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<Property> list = propertyService.getPropertyList(queryMap);
        model.addAttribute("list", list);
        Entity entity = entityService.getEntity(query.getEntityId());
        model.addAttribute("entity", entity);
        model.addAttribute("dataTypeEnums", DataTypeEnum.values());

        return "property/detail";
    }

    /**
     * 属性保存
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
    public String save(@RequestParam Long entityId, @RequestParam String[] enName, @RequestParam String[] cnName,
                       @RequestParam String[] dataType, @RequestParam String[] length,
                       @RequestParam String[] defaultValue, @RequestParam String[] isNull,
                       @RequestParam String[] primaryKey, @RequestParam String[] remark,
                       @RequestParam String[] sourceType, @RequestParam String[] source,
                       @RequestParam String[] checkGroup) {
        propertyService.updateProperty(entityId, enName, cnName, dataType, length, defaultValue, isNull, primaryKey,
                                       remark, sourceType, source, checkGroup);
        return redirect("/property/list.htm") + "?entityId=" + entityId;
    }

    /**
     * 属性删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long id) {
        propertyService.deleteProperty(id);
        return SUCCESS;
    }

    /**
     * 属性批量删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteBatch.htm")
    @ResponseBody
    public String deleteBatch(@RequestParam String ids) {
        propertyService.batchDeleteProperty(ids.split(","));
        return SUCCESS;
    }
}
