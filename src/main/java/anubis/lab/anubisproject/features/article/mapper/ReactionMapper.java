package anubis.lab.anubisproject.features.article.mapper;

import anubis.lab.anubisproject.features.article.dto.ReactionDTO;
import anubis.lab.anubisproject.features.article.entity.Reaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReactionMapper {

    public ReactionDTO fromReaction(Reaction reaction){
        ReactionDTO reactionDTO = new ReactionDTO();
        BeanUtils.copyProperties(reaction, reactionDTO);

        return reactionDTO;
    }
}
