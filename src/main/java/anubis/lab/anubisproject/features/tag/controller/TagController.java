package anubis.lab.anubisproject.features.tag.controller;

import anubis.lab.anubisproject.exceptions.ErrorEntity;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> addTag(@RequestBody Tag tag){
        try {
            return ResponseEntity.ok(tagService.addTag(tag));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idTag}")
    public ResponseEntity<?> updateTag(@PathVariable Long idTag, @RequestBody Tag tag){
        try {
            return ResponseEntity.ok(tagService.updateTag(idTag, tag));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @GetMapping("/{idTag}")
    public ResponseEntity<?> getTag(@PathVariable Long idTag){
        try {
            return ResponseEntity.ok(tagService.getTag(idTag));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }
    @GetMapping("name/{tagName}")
    public ResponseEntity<?> getTagByName(@PathVariable String tagName){
        try {
            return ResponseEntity.ok(tagService.getTagByName(tagName));
        }catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<?> getTags(){
        try {
            return ResponseEntity.ok(tagService.getTags());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTag(@PathVariable Long idTag){
        try {
            return ResponseEntity.ok(tagService.deleteTag(idTag));
        }catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

}
