package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class RequestHeader {

    @RequestMapping("/requestHeader")
    public void main(HttpServletRequest request) {

        Enumeration<String> e = request.getHeaderNames();

        while (e.hasMoreElements()) {
            String name = e.nextElement();
            System.out.println(name + " : " + request.getHeader(name));
        }
    }
}
