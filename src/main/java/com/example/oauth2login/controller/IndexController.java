package com.example.oauth2login.controller;

import com.example.oauth2login.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller // mustache 를 찾기 위해
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String feed(Model model) {
        model.addAttribute("posts", "Hello, Spring Security!");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

}
