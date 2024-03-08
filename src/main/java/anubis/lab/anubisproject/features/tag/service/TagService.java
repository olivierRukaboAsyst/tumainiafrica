package anubis.lab.anubisproject.features.tag.service;

import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public interface TagService extends GraphQLQueryResolver, GraphQLMutationResolver {

    TagDTO addTag(Tag tag);
    TagDTO updateTag(Long idTag, Tag tag);
    Tag getTag(Long idTag);
    Tag getTagByName(String tagName);
    List<TagDTO> getTags();
    Boolean deleteTag(Long idTag);



}
