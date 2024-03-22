package anubis.lab.anubisproject.features.comment.mapper;

import anubis.lab.anubisproject.features.comment.repository.CommentRepository;
import anubis.lab.anubisproject.features.comment.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;

@Service
public class CommentMapper{

    private final CommentRepository commentRepository;

    public CommentMapper(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO fromCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        Comment newComment = commentRepository.findById(comment.getId()).orElseThrow();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setDisplay(comment.isDisplay());
        commentDTO.setCustomerName(newComment.getCustomer().getFullname());
        return commentDTO;
    }

    public Comment fromComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        return comment;
    }

}