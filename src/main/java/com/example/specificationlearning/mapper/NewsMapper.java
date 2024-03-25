package com.example.specificationlearning.mapper;

import com.example.specificationlearning.dto.NewsRq;
import com.example.specificationlearning.dto.NewsRs;
import com.example.specificationlearning.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {

    NewsMapper INSTANCE = getMapper(NewsMapper.class);

    NewsRs toDto(News news);

    List<NewsRs> toListDto(List<News> newsList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    News toEntity(NewsRq newsRq);
}
