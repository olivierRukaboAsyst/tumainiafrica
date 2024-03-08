package anubis.lab.anubisproject.features.tag.mapper;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {
    public TagDTO fromTag(Tag tag){
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tag, tagDTO);

        return tagDTO;
    }

    public Tag fromTagDTO(TagDTO tagDTO){
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);

        return tag;
    }
}
