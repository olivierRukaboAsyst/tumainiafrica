package anubis.lab.anubisproject.features.tag.mapper;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagMapper {
    public TagDTO fromTag(Tag tag){
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tag, tagDTO);

        return tagDTO;
    }
    public List<TagDTO> fromTagList(List<Tag> tags){
        List<TagDTO> tagDTOS = new ArrayList<>();
        BeanUtils.copyProperties(tags, tagDTOS);

        return tagDTOS;
    }
    public Tag fromTagDTO(TagDTO tagDTO){
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);

        return tag;
    }
    public List<Tag> fromTagDTOS(List<TagDTO> tagDTOS){
        List<Tag> tagList = new ArrayList<>();
        BeanUtils.copyProperties(tagDTOS, tagList);

        return tagList;
    }
}
