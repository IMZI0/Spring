package com.example.news.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloUserController {
    @GetMapping("/hello")
    public String helloUser(Model model) {
        model.addAttribute("userName", "도혜지");
        return "news/helloUser";
    }
}