package anubis.lab.anubisproject.features.comment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.comment.service.CommentService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentControlleur {

    private CommentService commentService;

    @GetMapping("/{idComment}")
    public Comment getComment(@PathVariable Long idComment) {
        Comment comment = commentService.getComment(idComment);

        return comment;
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        List<CommentDTO> commentDTOs = commentService.getAllComments();

        return commentDTOs;
    }

    @PostMapping()
    public CommentDTO addComment(@RequestBody Comment comment, @RequestParam Long idArticle, @RequestParam String idCustomer) {
        return commentService.addComment(comment, idArticle, idCustomer);
    }

    @PutMapping("/{idComment}")
    public CommentDTO updateComment(@PathVariable Long idComment, @RequestParam Long idArticle, @RequestParam String idCustomer,
            @RequestBody Comment comment) {
        return commentService.updateComment(idComment, idArticle, idCustomer, comment);
    }

    @DeleteMapping("/{idComment}")
    public Boolean deleteComment(@PathVariable Long idComment) {
        return commentService.deleteComment(idComment);
    }

}