package com.example.news.Controller;

import com.example.news.Repository.NewsRepository;
import com.example.news.domain.News;
import com.example.news.dto.NewsDto;
import com.example.news.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;
    //의존성주입
    private final NewsMapper mapper;

    @GetMapping("/news/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/news/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post.toString());
        News n = mapper.newsPostDtoToNews(post);
        newsRepository.save(n);
        return "";
    }
    @GetMapping("/news/{newsId}")
    public String getNews(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        model.addAttribute("news",news);
        return "news/detail";
    }
    @GetMapping("news/list")
    public String getNewsList(Model model){
        List<News> newsList = newsRepository.findAll();
        model.addAttribute("newsList",newsList);
        return "news/list";
    }
}
