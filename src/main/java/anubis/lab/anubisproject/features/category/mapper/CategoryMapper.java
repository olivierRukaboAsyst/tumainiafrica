package anubis.lab.anubisproject.features.category.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;

@Service
public class CategoryMapper {
    
    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);

        return categoryDTO;
    }

    public Category fromCategoryDTO(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        return category;
    }


}
