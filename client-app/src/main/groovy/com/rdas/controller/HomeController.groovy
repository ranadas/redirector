package com.rdas.controller

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader

/**
 * Created by rdas on 04/07/2016.
 */
import org.springframework.web.bind.annotation.RequestMapping

import javax.servlet.http.HttpServletResponse

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(@RequestHeader(value = "Accept") String accept,
                       @RequestHeader(value = "Accept-Language") String acceptLanguage,
                       @RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent,
                       @RequestHeader(value = "sm_user", defaultValue = "sm_u_default_value") String smUser,
                       @RequestHeader HttpHeaders headers,
                       HttpServletResponse response) {
        System.out.println("accept: " + accept);
        System.out.println("acceptLanguage: " + acceptLanguage);
        System.out.println("userAgent: " + userAgent);
        System.out.println("smUser: " + smUser);


        return "index"
    }
}