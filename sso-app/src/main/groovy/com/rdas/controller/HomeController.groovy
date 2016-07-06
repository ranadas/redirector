package com.rdas.controller

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by rdas on 04/07/2016.
 */
import org.springframework.web.bind.annotation.RequestMethod

import javax.servlet.http.HttpServletResponse

@Slf4j
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void homeGet(HttpServletResponse response) {
        response.setHeader("sm_user", "ranapratapdas");
        //response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect("http://localhost:8080");
        // comment out the return because we are redirectng to a different url.
        //return "home"
    }
}