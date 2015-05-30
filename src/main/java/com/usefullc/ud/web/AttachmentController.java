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

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.ud.web.query.AttachmentQuery;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.service.IAttachmentService;

/**
 * 附件 Controller
 * @author tangss
 *
 * @2014-04-01 17
 */
@Controller
@RequestMapping(value="/attachment")
public class AttachmentController extends BaseController {
	

    @Autowired
    private IAttachmentService attachmentService;
    

    /**
     * 进入附件列表
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.htm")
    public String list(AttachmentQuery query, Model model) {
        Map<String,Object> queryMap = BeanUtils.beanToQueryMap(query);
        Pagination<Attachment> page = attachmentService.getAttachmentListPage(queryMap);
        model.addAttribute("page", page);
        return "attachment/list";
    }
    
    /**
     * 进入附件新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterNew.htm")
    public String enterNew(Model model){
    	return "attachment/new";
    }
    
    /**
     * 进入附件修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterEdit.htm")
    public String enterEdit(@RequestParam Long id,Model model){
    	Attachment domain = attachmentService.getAttachment(id);
    	model.addAttribute("domain", domain);
    	return "attachment/edit";
    }
    
    /**
     * 进入附件查看
     * @param model
     * @return
     */
    @RequestMapping(value = "/enterView.htm")
    public String enterView(@RequestParam Long id,Model model){
    	Attachment domain = attachmentService.getAttachment(id);
    	model.addAttribute("domain", domain);
    	return "attachment/view";
    }
    
    /**
     * 附件保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/save.htm")
	@ResponseBody
    public String save(Attachment domain){
    	attachmentService.insertAttachment(domain);
    	return SUCCESS;
    }
    
    /**
     * 附件修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm")
	@ResponseBody
    public String update(Attachment domain){
    	Attachment oldDomain = attachmentService.getAttachment(domain.getId());
    	BeanUtils.beanCopy(domain, oldDomain);
    	attachmentService.updateAttachment(oldDomain);
    	return SUCCESS;
    }
    
    /**
     * 附件删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.htm")
	@ResponseBody
    public String delete(@RequestParam Long id){
    	attachmentService.deleteAttachment(id);
    	return SUCCESS;
    }
	
	
	
	

}
