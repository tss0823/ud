/**
 * 
 */
package com.usefullc.ud.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.usefullc.ud.common.enums.ModuleEnum;
import com.usefullc.ud.domain.ConfigItem;
import com.usefullc.ud.service.IConfigItemService;

/**
 * 配置项 Controller
 * 
 * @author tangss
 * @2013-6-3 @上午10:02:29
 */
@Controller
@RequestMapping(value = "/configItem")
public class ConfigItemController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(ConfigItemController.class);

    @Autowired
    private IConfigItemService  configItemService;

    /**
     * 进入配置项列表
     * 
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(@RequestParam Long appId, Model model) {
        List<List<ConfigItem>> list = configItemService.getConfigItemListByAppId(appId);
        model.addAttribute("list", list);
        model.addAttribute("appId", appId);
        return "configItem/list";
    }

    /**
     * 进入配置项新增
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(@RequestParam Long appId, Model model) {
        model.addAttribute("appId", appId);
        model.addAttribute("moduleEnumList", ModuleEnum.values());
        return "configItem/new";
    }

    /**
     * 进入配置项修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long appId, @RequestParam String key, Model model) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        queryMap.put("key", key);
        List<ConfigItem> itemList = configItemService.getConfigItemList(queryMap);
        List<ConfigItem> sortItemList = new ArrayList<ConfigItem>();
        model.addAttribute("itemList", sortItemList);
        for (ModuleEnum me : ModuleEnum.values()) {
            String code = me.getCode();
            for (ConfigItem item : itemList) {
                if (item.getModule().equals(code)) {
                    sortItemList.add(item);
                    break;
                }
            }

        }
        model.addAttribute("appId", appId);
        model.addAttribute("key", key);
        return "configItem/edit";
    }

    /**
     * 进入所有配置项修改
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEditAll.htm")
    public String enterEditAll(@RequestParam Long appId, Model model) {
        List<List<ConfigItem>> list = configItemService.getConfigItemListByAppId(appId);
        model.addAttribute("list", list);
        model.addAttribute("appId", appId);
        return "configItem/editAll";
    }

    /**
     * 配置项保存
     * 
     * @param appId 应用ID
     * @param key 键
     * @param modules 模式集合
     * @param vals 值集合
     * @return
     */
    @RequestMapping(value = "/save.htm")
    @ResponseBody
    public String save(@RequestParam Long appId, @RequestParam String key, @RequestParam String modules[],
                       @RequestParam String vals[]) {
        configItemService.insertConfigItem(appId, key, modules, vals);
        return SUCCESS;
    }

    /**
     * 配置项修改
     * 
     * @param appId 应用ID
     * @param key 键
     * @param modules 模式集合
     * @param vals 值集合
     * @return
     */
    @RequestMapping(value = "/update.htm")
    @ResponseBody
    public String update(@RequestParam Long appId, @RequestParam String key, @RequestParam String modules[],
                         @RequestParam String vals[]) {
        configItemService.updateConfigItem(appId, key, modules, vals);
        return SUCCESS;
    }

    /**
     * 配置项修改所有
     * 
     * @param ids 模式集合
     * @param values 值集合
     * @return
     */
    @RequestMapping(value = "/updateAll.htm")
    @ResponseBody
    public String updateAll(@RequestParam Long ids[], @RequestParam String values[]) {
        configItemService.updateAllConfigItem(ids, values);
        return SUCCESS;
    }

    /**
     * 配置项删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
    @ResponseBody
    public String delete(@RequestParam Long appId, @RequestParam String key) {
        configItemService.deleteConfigItemByKey(appId, key);
        return SUCCESS;
    }

    /**
     * 配置项删除
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteAll.htm")
    @ResponseBody
    public String deleteAll(@RequestParam Long appId) {
        configItemService.deleteConfigItemByAppId(appId);
        return SUCCESS;
    }

    /**
     * 配置项打印
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/printConfig/{param}.htm")
    public void printConfig(@PathVariable String param, HttpServletResponse response) {
        Long appId = Long.valueOf(param.split("_")[0]);
        String module = param.split("_")[1];
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        queryMap.put("module", module);
        List<ConfigItem> itemList = configItemService.getConfigItemList(queryMap);
        StringBuilder sb = new StringBuilder();
        for (ConfigItem configItem : itemList) {
            sb.append(configItem.getKey());
            sb.append("=");
            sb.append(configItem.getValue());
            sb.append("\r\n");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(sb.toString());
        } catch (Exception e) {
            log.error("print stream failed! module=" + module + ",appId=" + appId, e);
        }
    }

    /**
     * prop文件长传并初始化
     * 
     * @param appId
     * @param propFile
     * @param module
     * @return
     */
    @RequestMapping(value = "/uploadProp.htm")
    @ResponseBody
    public String uploadProp(@RequestParam Long appId, @RequestParam MultipartFile propFile, String module) {
        configItemService.uploadProp(appId, propFile, module);
        return SUCCESS;
    }

}
