package anubis.lab.anubisproject.features.tag.service;

import anubis.lab.anubisproject.features.tag.mapper.TagMapper;
import anubis.lab.anubisproject.features.tag.Repository.TagRepository;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;
    private TagMapper mapper;

    @Override
    public TagDTO addTag(Tag tag) {
        Tag savedTag = tagRepository.save(tag);

        return mapper.fromTag(savedTag);
    }
    @Override
    public TagDTO updateTag(Long idTag, Tag requestTag) {
        Tag tag = getTag(idTag);
        try {
            if (requestTag != null){
                if (requestTag.getName() != null){
                    tag.setName(requestTag.getName());
                }
                if (requestTag.getDescription() != null){
                    tag.setDescription(requestTag.getDescription());
                }
                if (requestTag.getIconUrl() != null){
                    tag.setIconUrl(requestTag.getIconUrl());
                }
            }
            Tag updatedtag = tagRepository.save(tag);
            return mapper.fromTag(updatedtag);
        }catch (Exception c){
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }

    }

    @Override
    public Tag getTag(Long idTag) {
        Tag tag = tagRepository.findById(idTag)
                .orElseThrow(() -> new RuntimeException(String.format("Ce tag est introuvable")));
        return tag;
    }

    @Override
    public Tag getTagByName(String tagName){
        Tag tag = new Tag();
        try {
            tag = tagRepository.findByName(tagName);
        }catch (Exception e){
            throw new RuntimeException(String.format("Le tag %s n'est pas dans ce category", tagName));
        }
        return tag;
    }
    @Override
    public List<TagDTO> getTags() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()){
            throw new RuntimeException(String.format("Pas des tags enregistres"));
        }
        List<TagDTO> tagDTOS = tags.stream().map(t -> mapper.fromTag(t)).collect(Collectors.toList());
        return tagDTOS;
    }
    @Override
    public Boolean deleteTag(Long idTag) {
        getTag(idTag);
        tagRepository.deleteById(idTag);
        return true;
    }
}
