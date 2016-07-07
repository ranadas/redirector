package com.rdas.controller

import com.squareup.okhttp.Headers
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.squareup.okhttp.ResponseBody
import groovy.util.logging.Slf4j
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by rdas on 04/07/2016.
 */
import org.springframework.web.bind.annotation.RequestMethod

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Controller
public class HomeController {

    private final OkHttpClient httpClient;

    public HomeController() {
        this.httpClient = new OkHttpClient()
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void homeGet(HttpServletResponse response, HttpServletRequest request) {
        //response.setHeader("sm_user", "ranapratapdas");
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.sendRedirect("http://localhost:8080");
        // comment out the return because we are redirectng to a different url.
        //return "home"


        def url = "http://localhost:8080"

        Headers.Builder headersBuilder = new Headers.Builder();
        headersBuilder.add("sm_user", "ranapratapdas");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headersBuilder.add(name, request.getHeader(name));
        }

        Request newRequest = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .get()
                .build();

        Response okResponse = httpClient.newCall(newRequest).execute();
        ResponseBody body = okResponse.body();

        int httpStatusCode = okResponse.code();
        if (httpStatusCode == HttpStatus.OK.value()) {
            response.setContentType(MediaType.ALL_VALUE); //what's the content type? could be the one from okresponse, I guess
            response.setContentLengthLong(body.contentLength());
            response.setHeader("sm_user", "ranapratapdas");

            IOUtils.copy(body.byteStream(), response.getOutputStream());
            response.flushBuffer();
        } else {
            //what else?
        }
    }

    public void fancyProxy(HttpServletResponse response, HttpServletRequest request) {
        def url = "http://localhost:8080"
        com.squareup.okhttp.Headers.Builder headersBuilder = new Headers.Builder();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headersBuilder.add(name, request.getHeader(name));
        }

        //do the same with request parameters. i mean, retrieve them from your request and get them ready to be stuffed in new request
        Map<String, String[]> parameterMap = request.getParameterMap();


        String servletPath = request.getServletPath();
        println "servletPath = $servletPath"


        Request newRequest = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .get() //Warning, I am doing a GET method here
                .build();

        Response okResponse = httpClient.newCall(newRequest).execute();
        com.squareup.okhttp.ResponseBody body = okResponse.body();

        int httpStatusCode = okResponse.code();
        if (httpStatusCode == HttpStatus.OK.value()) {
            response.setContentType(MediaType.TEXT_PLAIN_VALUE); //what's the content type? could be the one from okresponse, I guess
            response.setContentLengthLong(body.contentLength());

            //response.setHeader("name", "value"); //you need to stuff all headers from okResponse

            IOUtils.copy(body.byteStream(), response.getOutputStream());
            response.flushBuffer();
        } else {
            //what else?
        }
    }
}