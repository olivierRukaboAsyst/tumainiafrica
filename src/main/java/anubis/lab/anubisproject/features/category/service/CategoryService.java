package anubis.lab.anubisproject.features.category.service;

import java.util.List;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface CategoryService extends GraphQLQueryResolver, GraphQLMutationResolver {

    CategoryDTO addCategory(Category category);
    CategoryDTO updateCategory(Long idCategory, Category category);
    Category getCategory(Long id);
    List<CategoryDTO> getAllCategories();
    Boolean deleteCategory(Long idCategory);

}
