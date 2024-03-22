package anubis.lab.anubisproject.features.tag.service;

import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;

import java.util.List;

public interface TagService {

    TagDTO addTag(Tag tag);
    TagDTO updateTag(Long idTag, Tag tag);
    Tag getTag(Long idTag);
    Tag getTagByName(String tagName);
    List<TagDTO> getTags();
    List<TagDTO> getAllTagsByIds(List<Long> ids);
    Boolean deleteTag(Long idTag);



}
