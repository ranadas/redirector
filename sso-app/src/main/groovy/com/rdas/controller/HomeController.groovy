package com.rdas.controller

/**
 * Created by rdas on 04/07/2016.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String homeGet(HttpServletResponse response) {
        response.setHeader("sm_user", "ranapratapdas");
        response.sendRedirect("http://localhost:8080");
        return "home"
    }

    @RequestMapping(method = RequestMethod.POST, value = "/greet")
    public String homePost() {
        return "homep"
    }
}