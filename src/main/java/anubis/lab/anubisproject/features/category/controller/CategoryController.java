package anubis.lab.anubisproject.features.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.service.CategoryService;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@AllArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        try {
            return ResponseEntity.ok(categoryService.addCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<?> updateCategory(@PathVariable Long idCategory, @RequestBody Category category){
        try {
            return ResponseEntity.ok(categoryService.updateCategory(idCategory, category));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<?> getCategory(@PathVariable Long idCategory){
        try {
            return ResponseEntity.ok(categoryService.getCategory(idCategory));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        try {
            return ResponseEntity.ok(categoryService.getAllCategories());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long idCategory){
        try {
            return ResponseEntity.ok(categoryService.deleteCategory(idCategory));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
