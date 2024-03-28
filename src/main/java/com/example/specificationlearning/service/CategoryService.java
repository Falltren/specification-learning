package com.example.specificationlearning.service;

import com.example.specificationlearning.dto.CategoryDto;
import com.example.specificationlearning.entity.Category;
import com.example.specificationlearning.exception.BadRequestException;
import com.example.specificationlearning.mapper.CategoryMapper;
import com.example.specificationlearning.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BadRequestException(getMessageForException(id)));
        return CategoryMapper.INSTANCE.toDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.toListDto(categories);
    }


    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDto);
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.toDto(category);
    }

    public Category getSavedCategory(String categoryTitle) {
        Optional<Category> categoryOptional = categoryRepository.findByTitle(categoryTitle);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        Category category = new Category();
        category.setTitle(categoryTitle);
        return categoryRepository.save(category);
    }

    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new BadRequestException("поле ID должно быть заполнено");
        }
        Category category = categoryRepository.findById(categoryDto.getId()).orElseThrow(
                () -> new BadRequestException(getMessageForException(categoryDto.getId())));
        CategoryMapper.INSTANCE.updateCategoryFromDto(categoryDto, category);
        return CategoryMapper.INSTANCE.toDto(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new BadRequestException(getMessageForException(id));
        }
        categoryRepository.deleteById(id);
    }

    private String getMessageForException(Long id) {
        return "Категория с ID " + id + " не найдена.";
    }
}
