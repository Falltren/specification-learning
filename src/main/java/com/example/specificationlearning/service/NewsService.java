package com.example.specificationlearning.service;

import com.example.specificationlearning.dto.NewsRq;
import com.example.specificationlearning.dto.NewsRs;
import com.example.specificationlearning.entity.Category;
import com.example.specificationlearning.entity.News;
import com.example.specificationlearning.entity.News_;
import com.example.specificationlearning.exception.BadRequestException;
import com.example.specificationlearning.mapper.NewsMapper;
import com.example.specificationlearning.repository.NewsRepository;
import com.example.specificationlearning.specification.Specs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoryService categoryService;

    public NewsRs getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new BadRequestException(getMessageForException(id)));
        return NewsMapper.INSTANCE.toDto(news);
    }

    public List<NewsRs> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return NewsMapper.INSTANCE.toListDto(newsList);
    }

    @Transactional
    public NewsRs createNews(NewsRq newsRq) {
        News news = NewsMapper.INSTANCE.toEntity(newsRq);
        news.setDate(Instant.now());
        Category category = categoryService.getSavedCategory(newsRq.getCategory());
        news.setCategory(category);
        newsRepository.save(news);
        return NewsMapper.INSTANCE.toDto(news);
    }

    public void deleteNews(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new BadRequestException(getMessageForException(id));
        }
        newsRepository.deleteById(id);
    }

    @Transactional
    public NewsRs updateNews(NewsRq newsRq) {
        if (newsRq.getId() == null) {
            throw new BadRequestException("Поле ID должно быть заполнено");
        }
        News news = newsRepository.findById(newsRq.getId()).orElseThrow(
                () -> new BadRequestException(getMessageForException(newsRq.getId())));
        news.setDate(Instant.now());
        Category category = categoryService.getSavedCategory(newsRq.getCategory());
        news.setCategory(category);
        NewsMapper.INSTANCE.updateNewsFromDto(newsRq, news);
        return NewsMapper.INSTANCE.toDto(news);
    }

    public List<NewsRs> searchNews(String title, String text) {
        List<News> news = newsRepository.findAll(Specification.where(Specs.eq(News_.title, title))
                .and(Specs.eq(News_.text, text)));
        return NewsMapper.INSTANCE.toListDto(news);
    }

    private String getMessageForException(Long id) {
        return "Новость с ID " + id + " не найдена.";
    }
}
