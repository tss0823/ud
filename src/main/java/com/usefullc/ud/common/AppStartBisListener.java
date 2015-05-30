/**
 * 
 */
package com.usefullc.ud.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.usefullc.platform.common.CommConstant;

/**
 * app 启动业务监听器
 * 
 * @author tangss
 * @2013年10月10日 @下午1:51:20
 */
public class AppStartBisListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setAttribute(CommConstant.LINK_MAP, ParseResourceLink.getLinkMap());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
