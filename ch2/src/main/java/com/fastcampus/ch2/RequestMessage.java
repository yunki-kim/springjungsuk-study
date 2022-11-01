package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Enumeration;

@Controller
public class RequestMessage {

    @RequestMapping("/requestMessage")
    public void main(HttpServletRequest request) throws Exception {

        // 1. request line
        String requestLine = request.getMethod(); // GET or POST
        requestLine += " " + request.getRequestURI(); // /requestMessage

        String queryString = request.getQueryString(); // year=2021&month=10&day=1
        requestLine += queryString == null ? "" : "?" + queryString;
        requestLine += " " + request.getProtocol(); // HTTP/1.1
        System.out.println(requestLine);

        // 2. requeset headers
        Enumeration<String> e = request.getHeaderNames();

        while (e.hasMoreElements()) {
            String name = e.nextElement();
            System.out.println(name + " : " + request.getHeader(name));
        }

        // 3. request body - POST 일 때만!
        final  int CONTENT_LENGTH = request.getContentLength();

        if (CONTENT_LENGTH > 0) {
            byte[] content = new byte[CONTENT_LENGTH];

            InputStream in = request.getInputStream();
            in.read(content, 0, CONTENT_LENGTH);

            System.out.println();
            System.out.println(new String(content, "UTF-8"));
        }
    }
}
