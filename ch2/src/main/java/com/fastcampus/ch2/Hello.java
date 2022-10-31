package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

    int iv = 10;
    static int cv = 20;

    @RequestMapping("/hello")
    private static void main() {
        System.out.println("Hello");
        // System.out.println(iv);
        System.out.println(cv);
    }
}
