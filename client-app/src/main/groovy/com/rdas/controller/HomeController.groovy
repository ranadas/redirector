package com.rdas.controller

import com.rdas.model.Message
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestHeader

/**
 * Created by rdas on 04/07/2016.
 */
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(@RequestHeader(value = "Accept") String accept,
                       @RequestHeader(value = "Accept-Language") String acceptLanguage,
                       @RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent,
                       @RequestHeader(value = "sm_user", defaultValue = "sm_u_default_value") String smUser,
                       @RequestHeader HttpHeaders headers,
                       HttpServletResponse response) {
        println "accept: $accept"
        println "acceptLanguage: $acceptLanguage"
        println "userAgent: $userAgent"
        println "smUser: $smUser"

        def set = headers.entrySet()

        println set

        return "index"
    }


    @RequestMapping(path="/pst", method = RequestMethod.POST)
    public ModelAndView create() {

    }
}