/**
 * 
 */
package com.usefullc.ud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @2014-03-20 15
 */
@Controller
public class FrameController extends BaseController {

    @RequestMapping(value = "/index.htm")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/enterLogin.htm")
    public String enterLogin() {
        return "/main/login";
    }

    @RequestMapping(value = "/frame.htm")
    public String frame() {
        return "frame";
    }

    @RequestMapping(value = "/top.htm")
    public String top() {
        return "/main/top";
    }

    @RequestMapping(value = "/left.htm")
    public String left() {
        return "/main/left";
    }

    @RequestMapping(value = "/nav.htm")
    public String nav() {
        return "/main/nav";
    }

    @RequestMapping(value = "/right.htm")
    public String right() {
        return "/main/right";
    }

    @RequestMapping(value = "/403.htm")
    public String to403() {
        return "403";
    }

    @RequestMapping(value = "/404.htm")
    public String to404() {
        return "404";
    }

    @RequestMapping(value = "/500.htm")
    public String to500() {
        return "500";
    }
}
