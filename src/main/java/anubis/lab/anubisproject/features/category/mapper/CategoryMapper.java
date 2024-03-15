package anubis.lab.anubisproject.features.category.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryMapper {
    
    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);

        return categoryDTO;
    }

    public List<CategoryDTO> fromCategoryList(List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        BeanUtils.copyProperties(categories, categoryDTOS);

        return categoryDTOS;
    }
    public Set<CategoryDTO> fromCategorySet(Set<Category> categories){
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        BeanUtils.copyProperties(categories, categoryDTOS);

        return categoryDTOS;
    }

    public Category fromCategoryDTO(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        return category;
    }

    public List<Category> fromCategoryDTOList(List<CategoryDTO> categoryDTOS){
        List<Category> categories = new ArrayList<>();
        BeanUtils.copyProperties(categoryDTOS, categories);

        return categories;
    }

    public Set<Category> fromCategoryDTOSet(Set<CategoryDTO> categoryDTOS){
        Set<Category> categories = new HashSet<>();
        BeanUtils.copyProperties(categoryDTOS, categories);

        return categories;
    }


}
