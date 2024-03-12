package anubis.lab.anubisproject.features.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.helpers.Constant;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleResolver articleResolver;
    private final CustomerService customerService;
    private final CommentMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleResolver articleResolver, CustomerService customerService, CommentMapper mapper) {
        this.commentRepository = commentRepository;
        this.articleResolver = articleResolver;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO addComment(Comment comment, Long idArticle, String idCustomer) {
        Article article = this.articleResolver.getArticle(idArticle);
        Customer customer = this.customerService.getCustomer(idCustomer);
        try {
            if (idArticle == null | idCustomer == null | comment.getContent() == null){
                throw new RuntimeException(String.format("Tout les champs sont obligatoire"));
            }else {
                comment.setArticle(article);
                comment.setCustomer(customer);
            }
            comment.setCreatedAt(new Constant().dateFormated());
            Comment savedComment = commentRepository.save(comment);
            return mapper.fromCommentDTO(savedComment);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de l'ajout de commentaire"));
        }
    }

    @Override
    public Comment getComment(Long idComment) {
        return commentRepository.findById(idComment)
                .orElseThrow(() -> new RuntimeException("Ce commentaire n'existe pas"));
    }

    @Override
    public CommentDTO updateComment(Long idComment, Long idArticle, String idCustomer, Comment requestComment) {
        Comment comment = getComment(idComment);
        Article article = articleResolver.getArticle(idArticle);
        Customer customer = customerService.getCustomer(idCustomer);
        try {
            if (comment != null) {
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
            comment.setUpdatedAt(new Constant().dateFormated());

            Comment savedComment = commentRepository.save(comment);
            return mapper.fromCommentDTO(savedComment);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            throw new RuntimeException(String.format("Pas des commentaires pour l'instant"));
        }
        List<CommentDTO> commentDTOs = comments.stream().map(c -> mapper.fromCommentDTO(c)).collect(Collectors.toList());
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
