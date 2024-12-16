package com.example.news.Controller;

import com.example.news.Repository.NewsRepository;
import com.example.news.domain.News;
import com.example.news.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;

    @GetMapping("/news/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/news/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post.toString());
        News n = News.toEntity(post);
        newsRepository.save(n);
        return "";
    }
}
