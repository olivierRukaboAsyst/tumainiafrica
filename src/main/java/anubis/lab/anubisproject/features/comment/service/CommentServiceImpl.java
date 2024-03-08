package anubis.lab.anubisproject.features.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.service.ArticleResolver;
import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.comment.mapper.CommentMapper;
import anubis.lab.anubisproject.features.comment.repository.CommentRepository;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import anubis.lab.anubisproject.features.customer.service.CustomerService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ArticleResolver articleResolver;
    private CustomerService customerService;
    private CommentMapper mapper;

    @Override
    public CommentDTO addComment(Comment comment, Long idArticle, String idCustomer) {
        Article article = articleResolver.getArticle(idArticle);
        Customer customer = customerService.getCustomer(idCustomer);
        try {
            comment.setArticle(article);
            comment.setCustomer(customer);
            Comment savedComment = commentRepository.save(comment);
            return mapper.fromCmmentDTO(savedComment);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de l'ajout de commentaire"));
        }

    }

    @Override
    public Comment getComment(Long idComment) {
        Comment comment = commentRepository.findById(idComment)
                .orElseThrow(() -> new RuntimeException("Ce commentaire n'existe pas"));
        return comment;
    }

    @Override
    public CommentDTO updateComment(Long idComment, Long idArticle, String idCustomer, Comment requestComment) {
        Comment comment = getComment(idComment);
        Article article = articleResolver.getArticle(idArticle);
        Customer customer = customerService.getCustomer(idCustomer);
        try {
            if (comment != null) {
                if (idComment == null | idArticle == null | idCustomer == null | comment.getContent() == null){
                    throw new RuntimeException(String.format("Tout les champs sont obligatoire"));
                }else{
                    if (idArticle != null) {
                        comment.setArticle(article);
                    }
                    if (idCustomer != null) {
                        comment.setCustomer(customer);
                    }
                    if (requestComment.getContent() != null) {
                        comment.setContent(requestComment.getContent());
                    }
                }
            }

            Comment savedComment = commentRepository.save(comment);
            return mapper.fromCmmentDTO(savedComment);
        } catch (

        Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            throw new RuntimeException(String.format("Pas des commentaires pour l'instant"));
        }
        List<CommentDTO> commentDTOs = comments.stream().map(c -> mapper.fromCmmentDTO(c)).collect(Collectors.toList());
        return commentDTOs;
    }

    @Override
    public Boolean deleteComment(Long idComment) {
        getComment(idComment);
        try {
            commentRepository.deleteById(idComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
