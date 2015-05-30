/**
 * 
 */
package com.usefullc.ud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangss
 * @2013年9月28日 @下午3:20:11
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends BaseController {

    @RequestMapping(value = "/main.htm")
    public String main() {
        return "/main/main";
    }
}
