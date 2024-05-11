package vn.edu.ptit.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.quiz.entity.Category;
import vn.edu.ptit.quiz.exception.NotFoundException;
import vn.edu.ptit.quiz.model.CategoryAM;
import vn.edu.ptit.quiz.model.CategoryDto;
import vn.edu.ptit.quiz.repository.CategoryRepo;
import vn.edu.ptit.quiz.service.CategoryService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(category -> ConverterUtil.mappingToObject(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        return ConverterUtil.mappingToObject(category, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryAM categoryAM) {
        Category category = ConverterUtil.mappingToObject(categoryAM, Category.class);
        categoryRepo.save(category);
        return ConverterUtil.mappingToObject(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryAM categoryAM) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        category.setCategoryName(categoryAM.getCategoryName());
        category.setDescription(categoryAM.getDescription());
        categoryRepo.save(category);
        return ConverterUtil.mappingToObject(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        categoryRepo.delete(category);
    }
}
