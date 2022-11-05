package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller
@RequestMapping("/register")
public class RegisterController {

    // @RequestMapping(value = "/register/add", method = RequestMethod.GET)
    @GetMapping("/add") // 신규회원 가입 화면
    public String register() {
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    // @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(User user, Model model) throws Exception{

        // 1. 유효성 검사
        if (!isValid(user)) {
            String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");

            model.addAttribute("msg", msg);
            return "redirect:/register/add";
            // return "redirect:/register/add?msg=" + msg; // URL 재작성(rewriting)
        }

        // 2. DB에 신규회원 정보를 저장
        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
