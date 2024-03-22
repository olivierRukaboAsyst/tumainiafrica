package anubis.lab.anubisproject.features.comment.service;

import java.util.List;

import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;

public interface CommentService {

    CommentDTO addComment(Comment comment, Long idArticle, String idCustomer);
    Comment getComment(Long id);
    CommentDTO updateComment(Long idComment, Long idArticle, String idCustomer, Comment comment);
    List<CommentDTO> getAllComments();
    Boolean deleteComment(Long idComment);
}
