package anubis.lab.anubisproject.features.category.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.service.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public CategoryDTO addCategory(@RequestBody Category category){
        try {
            return categoryService.addCategory(category);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Echec d'Enregistrement, reessayer"));
        }
    }

    @PutMapping("/{idCategory}")
    public CategoryDTO updateCategory(@PathVariable Long idCategory, @RequestBody Category category){
        return categoryService.updateCategory(idCategory, category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }
    
    @DeleteMapping("/{idCategory}")
    public Boolean deleteCategory(@PathVariable Long idCategory){
        return categoryService.deleteCategory(idCategory);
    }
    
}
