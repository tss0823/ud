package com.usefullc.ud.web;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.common.enums.DataTypeEnum;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IPropertyService;
import com.usefullc.ud.web.form.CommonJsonForm;
import com.usefullc.ud.web.query.EntityQuery;
import com.usefullc.ud.web.query.PropertyQuery;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by shan on 2017/8/29.
 */
@Controller
@RequestMapping("api")
public class ApiController extends BaseController {

    @Autowired
    private IEntityService entityService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IPropertyService propertyService;


    @RequestMapping("entityList.do")
    @ResponseBody
    public Pagination<Entity> entityList(EntityQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Entity> page = entityService.getEntityListPage(queryMap);
        return page;
    }


    @RequestMapping("entitySave.do")
    @ResponseBody
    public CommonJsonForm entitySave(Entity domain) {
        entityService.insertEntity(domain);
        return new CommonJsonForm(1);
    }

    @RequestMapping("entityUpdate.do")
    @ResponseBody
    public CommonJsonForm entityUpdate(Entity domain) {
        Assert.notNull(domain.getId(),"id不能为空");
        entityService.updateEntity(domain);
        return new CommonJsonForm(1);
    }

    @RequestMapping("entityDetail.do")
    @ResponseBody
    public CommonJsonForm entityDetail(@RequestParam Long id) {
        Entity domain = entityService.getEntity(id);
        return new CommonJsonForm(SUCCESS,domain);
    }

    @RequestMapping("getEntityByEnName.do")
    @ResponseBody
    public CommonJsonForm getEntityByEnName(@RequestParam String enName) {
        Entity domain = entityService.getEntityByEnName(enName);
        return new CommonJsonForm(SUCCESS,domain);
    }

    @RequestMapping("entityDelete.do")
    @ResponseBody
    public CommonJsonForm entityDelete(@RequestParam Long id) {
        entityService.deleteEntity(id);
        return new CommonJsonForm(1);
    }

    @RequestMapping("entityCopy.do")
    @ResponseBody
    public CommonJsonForm entityCopy(@RequestParam Long id) {
        entityService.copy(id);
        return new CommonJsonForm(1);
    }

    @RequestMapping("buildSql.do")
    @ResponseBody
    public CommonJsonForm buildSql(@RequestParam Long appId, @RequestParam String ids) {
        String str = applicationService.buildSql(appId, ids.split(","));
        CommonJsonForm frm = new CommonJsonForm(SUCCESS, str);
        return frm;
    }

    @RequestMapping("buildApp.do")
    @ResponseBody
    public CommonJsonForm buildApp(@RequestParam Long appId, @RequestParam String ids) {
        Long genId = applicationService.buildApp(appId, ids.split(","));
        String url = "http://test.doublefit.cn:8083/genFile/download.htm?id="+genId;
        return new CommonJsonForm(SUCCESS,url);
    }

    @RequestMapping("propertyList.do")
    @ResponseBody
    public CommonJsonForm propertyList(PropertyQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToQueryMap(query);
        List<Property> dataList = propertyService.getPropertyList(queryMap);
        return new CommonJsonForm(SUCCESS,dataList);
    }

    @RequestMapping("dataTypeEnums.do")
    @ResponseBody
    public CommonJsonForm dataTypeEnums() {
        DataTypeEnum[] dataTypeEnums = DataTypeEnum.values();
        Map<String,String> dataMap = new HashedMap();
        for (DataTypeEnum dataTypeEnum : dataTypeEnums) {
            dataMap.put(dataTypeEnum.name(),dataTypeEnum.getValue());
        }
        return new CommonJsonForm(SUCCESS,dataMap);
    }

    @RequestMapping("propertySave.do")
    @ResponseBody
    public CommonJsonForm propertySave(@RequestParam Long entityId, @RequestParam String[] enName, @RequestParam String[] cnName,
                       @RequestParam String[] dataType, @RequestParam String[] length,
                       @RequestParam String[] defaultValue, @RequestParam String[] isNull,
                       @RequestParam String[] primaryKey, @RequestParam(required = false) String[] remark,
                       @RequestParam(required = false) String[] sourceType, @RequestParam(required = false) String[] source,
                       @RequestParam(required = false) String[] checkGroup) {
        propertyService.updateProperty(entityId, enName, cnName, dataType, length, defaultValue, isNull, primaryKey,
                remark, sourceType, source, checkGroup);
        return new CommonJsonForm(1);
    }



    @RequestMapping("propertyDelete.do")
    @ResponseBody
    public CommonJsonForm propertyBatchDelete(@RequestParam String ids) {
        propertyService.batchDeleteProperty(ids.split(","));
        return new CommonJsonForm(1);
    }

}
