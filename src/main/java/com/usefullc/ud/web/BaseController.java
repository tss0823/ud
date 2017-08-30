/**
 * 
 */
package com.usefullc.ud.web;

import com.usefullc.platform.common.utils.JsonUtil;
import com.usefullc.platform.web.AuthoritySpringController;
import com.usefullc.ud.web.form.CommonJsonForm;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${author}
 * @2013-10-12 13
 */
public class BaseController extends AuthoritySpringController {

    @ExceptionHandler
//    @ResponseBody
    public void resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String requestURI = request.getRequestURI();
        if(requestURI.endsWith(".do")){  // go ajax
            response.addHeader("Content-Type","application/json;charset=UTF-8");
            CommonJsonForm commonJsonForm = new CommonJsonForm("0", ExceptionUtils.getFullStackTrace(ex));
            String resultData = JsonUtil.toStr(commonJsonForm);
            try {
                response.getWriter().write(resultData);
            } catch (IOException e) {
                log.error("write error",e);
            }
        }else{  // go page
            response.setHeader("Location","/500.htm");
        }

    }
}
