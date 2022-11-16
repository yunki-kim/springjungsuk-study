package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@Controller
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200 -> 500
    public String catcher(Exception e, Model model) {
        System.out.println("model" + model);
        System.out.println("catcher() in ExceptionController");
        // model.addAttribute("ex", e);
        return "error";
    }

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception e, Model model) {
        model.addAttribute("ex", e);
        return "error";
    }

    @RequestMapping("/ex")
    public String main(Model model) throws Exception {
        model.addAttribute("msg", "message from ExceptionController.main()");
        throw new Exception("예외가 발생했습니다.");
    }

    @RequestMapping("/ex2")
    public String main2() throws Exception {
        throw new FileNotFoundException("예외가 발생했습니다.");
    }
}
