package com.rdas.controller

import com.squareup.okhttp.*
import groovy.util.logging.Slf4j
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by david on 11/07/2016.
 */

@Slf4j
@RestController
@RequestMapping(value = ProxyController.PROXY_CONTEXT_PATH)
public class ProxyController {
    public static final String PROXY_CONTEXT_PATH = "/proxy";

    private final OkHttpClient httpClient;

    public ProxyController() {
        this.httpClient = new OkHttpClient()
    }

    @RequestMapping(method = RequestMethod.GET, value = "/**")
    public void proxying(HttpServletResponse response, HttpServletRequest request) {


        String servletPath = request.getServletPath();
        println "servletPath = $servletPath"

        def url = "http://localhost:8080" + servletPath.substring(PROXY_CONTEXT_PATH.length())

        println "url = $url"

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
            response.setContentType(MediaType.ALL_VALUE);
            response.setContentLengthLong(body.contentLength());
            response.setHeader("sm_user", "ranapratapdas");
            IOUtils.copy(body.byteStream(), response.getOutputStream());
            response.flushBuffer();
        } else {
            //TODO
        }
    }
}
