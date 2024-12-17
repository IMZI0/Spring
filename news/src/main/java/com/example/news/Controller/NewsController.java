package com.example.news.Controller;

import com.example.news.Repository.NewsRepository;
import com.example.news.domain.News;
import com.example.news.dto.NewsDto;
import com.example.news.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository newsRepository;
    //의존성주입
    private final NewsMapper mapper;

    @GetMapping("/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post.toString());
        News n = mapper.newsPostDtoToNews(post);
        newsRepository.save(n);
        return "redirect:/news/" + n.getNewsId();
    }
    @GetMapping("/{newsId}")
    public String getNews(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 뉴스가 존재하지 않습니다."));
        model.addAttribute("news",news);
        return "news/detail";
    }
    @GetMapping("/list")
    public String getNewsList(Model model,
                              @RequestParam(name = "page", defaultValue = "0")int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<News> newsPage = newsRepository.findAll(pageable);
        model.addAttribute("newsPage", newsPage);

        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("hasNext",newsPage.hasNext());
        model.addAttribute("hasPrev",newsPage.hasPrevious());
        return "news/list";
    }
    @GetMapping("/{newsId}/delete")
    public String deleteNews(@PathVariable("newsId") Long newsId){
        newsRepository.deleteById(newsId);
        return "redirect:/news/list";
    }

    @GetMapping("/{newsId}/edit")
    public String editNewsForm(@PathVariable("newsId") Long newsId, Model model){
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 뉴스가 존재하지 않습니다."));
        model.addAttribute("news",news);
        return "news/edit";
    }

    @PostMapping("/{newsId}/update")
    public String editNews(@PathVariable("newsId") Long newsId, NewsDto.Patch patch) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 뉴스가 존재하지 않습니다."));
        mapper.PatchDtoToNews(patch, news);
        newsRepository.save(news);
        return "redirect:/news/" + news.getNewsId();
    }

}

