package com.example.specificationlearning.mapper;

import com.example.specificationlearning.dto.CategoryDto;
import com.example.specificationlearning.entity.Category;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryMapper INSTANCE = getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);

    List<CategoryDto> toListDto(List<Category> categories);

    Category toEntity(CategoryDto categoryDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryDto categoryDto, @MappingTarget Category category);
}
