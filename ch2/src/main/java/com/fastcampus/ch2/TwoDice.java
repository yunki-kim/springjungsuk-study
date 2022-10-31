package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TwoDice {

    @RequestMapping("/rollDice")
    public void main(HttpServletRequest request, HttpServletResponse response) {

    }
}
