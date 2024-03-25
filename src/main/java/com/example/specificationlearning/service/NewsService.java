package com.example.specificationlearning.service;

import com.example.specificationlearning.dto.NewsRq;
import com.example.specificationlearning.dto.NewsRs;
import com.example.specificationlearning.entity.News;
import com.example.specificationlearning.exception.NotFoundException;
import com.example.specificationlearning.mapper.NewsMapper;
import com.example.specificationlearning.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsRs getNews(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Новость с ID " + id + " не найдена."));
        return NewsMapper.INSTANCE.toDto(news);
    }

    public List<NewsRs> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return NewsMapper.INSTANCE.toListDto(newsList);
    }

    public NewsRs createNews(NewsRq newsRq) {
        News news = NewsMapper.INSTANCE.toEntity(newsRq);
        news.setDate(Instant.now());
        newsRepository.save(news);
        return NewsMapper.INSTANCE.toDto(news);
    }

    public void deleteNews(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new NotFoundException("Новость с ID " + id + " не найдена.");
        }
        newsRepository.deleteById(id);
    }

    @Transactional
    public NewsRs updateNews(NewsRq newsRq) {
        News news = newsRepository.findById(newsRq.getId()).orElseThrow(() -> new NotFoundException("Новость с ID " + newsRq.getId() + " не найдена."));
        news.setDate(Instant.now());
        NewsMapper.INSTANCE.updateNewsFromDto(newsRq, news);
        return NewsMapper.INSTANCE.toDto(news);
    }
}
