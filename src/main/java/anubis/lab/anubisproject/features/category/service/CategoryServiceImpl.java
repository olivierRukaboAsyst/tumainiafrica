package anubis.lab.anubisproject.features.category.service;

import java.util.List;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.helpers.Constant;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.mapper.CategoryMapper;
import anubis.lab.anubisproject.features.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper mapper;
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(Category category) {
        category.setCreatedAt(new Constant().dateFormated());
        Category savedCategory = categoryRepository.save(category);
        return mapper.fromCategory(savedCategory);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Ce categorie n'existe pas")));
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory, Category requestCategory) {
        Category category = getCategory(idCategory);
        try {
            if (requestCategory != null) {
                if (requestCategory.getName() != null) {
                    category.setName(requestCategory.getName());
                }
                if (requestCategory.getDescription() != null) {
                    category.setDescription(requestCategory.getDescription());
                }
                category.setUpdatedAt(new Constant().dateFormated());
            }

            Category savedCategory = categoryRepository.save(category);
            return mapper.fromCategory(savedCategory);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lor de la modification"));
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException(String.format("Pas des categories pour l'instant"));
        }
        List<CategoryDTO> categoryDTO = categories.stream().map(c -> mapper.fromCategory(c))
                .collect(Collectors.toList());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategoriesByIds(List<Long> idCategories) {
        List<Category> categories = categoryRepository.findAllById(idCategories);
        if (categories.isEmpty()) {
            throw new RuntimeException(String.format("Pas des categories avec ces ids"));
        }
        List<CategoryDTO> categoryDTO = categories.stream().map(c -> mapper.fromCategory(c))
                .collect(Collectors.toList());
        return categoryDTO;
    }

    @Override
    public Boolean deleteCategory(Long idCategory) {
        getCategory(idCategory);
        categoryRepository.deleteById(idCategory);

        return true;
    }

}
