package com.usefullc.ud.common;

import com.usefullc.platform.common.utils.JsonUtil;
import com.usefullc.ud.web.form.CommonJsonForm;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler implements HandlerExceptionResolver {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith(".do")) {  // go ajax
            response.addHeader("Content-Type", "application/json;charset=UTF-8");
            CommonJsonForm commonJsonForm = new CommonJsonForm(0, ExceptionUtils.getFullStackTrace(ex));
            String resultData = JsonUtil.toStr(commonJsonForm);
            try {
                response.getWriter().write(resultData);
                response.getWriter().close();
            } catch (IOException e) {
                log.error("write error", e);
            }
        } else {  // go page
            return new ModelAndView("" + response.getStatus());
//            response.setHeader("Location","/500.htm");
        }
        return null;
    }

}  