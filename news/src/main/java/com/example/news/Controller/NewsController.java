package com.example.news.Controller;

import com.example.news.dto.NewsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewsController {
    @GetMapping("/news/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/news/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post.toString());
        return "";
    }
}
