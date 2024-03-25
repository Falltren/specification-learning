package com.example.specificationlearning.mapper;

import com.example.specificationlearning.dto.NewsRq;
import com.example.specificationlearning.dto.NewsRs;
import com.example.specificationlearning.entity.News;
import org.mapstruct.*;

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

    @Mapping(target = "date", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNewsFromDto(NewsRq newsRq, @MappingTarget News news);
}
