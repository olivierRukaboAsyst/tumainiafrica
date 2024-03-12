package anubis.lab.anubisproject.features.comment.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;

@Service
public class CommentMapper{
    
    public CommentDTO fromCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment, commentDTO);
        return commentDTO;
    }

    public Comment fromComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        return comment;
    }

}