package com.fastcampus.ch2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

// @ControllerAdvice("com.fastcampus.ch2") // 지정된 패키지에서 발생한 예외만 처리
@ControllerAdvice // 모든 패키지에 적용
public class GlobalCatcher {

    @ExceptionHandler(Exception.class)
    public String catcher(Exception e, Model model) {
        System.out.println("catcher() in GlobalCatcher");
        model.addAttribute("ex", e);
        return "error";
    }

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception e, Model model) {
        model.addAttribute("ex", e);
        return "error";
    }
}
