package anubis.lab.anubisproject.features.category.service;

import java.util.List;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;

public interface CategoryService{

    CategoryDTO addCategory(Category category);
    CategoryDTO updateCategory(Long idCategory, Category category);
    Category getCategory(Long id);
    List<CategoryDTO> getAllCategories();
    List<CategoryDTO> getAllCategoriesByIds(List<Long> ids);
    Boolean deleteCategory(Long idCategory);

}
