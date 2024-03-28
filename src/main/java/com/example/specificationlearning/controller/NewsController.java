package com.example.specificationlearning.controller;

import com.example.specificationlearning.dto.NewsRq;
import com.example.specificationlearning.dto.NewsRs;
import com.example.specificationlearning.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/{id}")
    public NewsRs getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }

    @GetMapping
    public List<NewsRs> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NewsRs createNews(@RequestBody @Valid NewsRq newsRq) {
        return newsService.createNews(newsRq);
    }

    @PutMapping
    public NewsRs updateNews(@RequestBody @Valid NewsRq newsRq) {
        return newsService.updateNews(newsRq);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.deleteNews(id);
    }

    @GetMapping("/search")
    public List<NewsRs> searchNews(@RequestParam(required = false) String title,
                                   @RequestParam(required = false) String text) {
        return newsService.searchNews(title, text);
    }
}
