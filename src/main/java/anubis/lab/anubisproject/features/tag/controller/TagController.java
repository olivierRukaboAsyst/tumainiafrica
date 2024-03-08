package anubis.lab.anubisproject.features.tag.controller;

import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    @PostMapping
    public TagDTO addTag(@RequestBody Tag tag){
        try {
            return tagService.addTag(tag);
        }catch (Exception e){
            throw new RuntimeException(String.format("Echec lors de l'ajout du tag"));
        }
    }

    @PutMapping
    public TagDTO updateTag(@PathVariable Long idTag, @RequestBody Tag tag){
        try {
            return tagService.updateTag(idTag, tag);
        }catch (Exception e){
            throw new RuntimeException(String.format("Echec lors de la modification du tag"));
        }
    }

    @GetMapping("/{idTag}")
    public Tag getTag(@PathVariable Long idTag){
        return tagService.getTag(idTag);
    }
    @GetMapping("/{tagName}")
    public Tag getTagByName(@PathVariable String tagName){
        return tagService.getTagByName(tagName);
    }
    @GetMapping
    public List<TagDTO> getTags(){
        return tagService.getTags();
    }
    @DeleteMapping
    public Boolean deleteTag(@PathVariable Long idTag){
        return tagService.deleteTag(idTag);
    }

}
