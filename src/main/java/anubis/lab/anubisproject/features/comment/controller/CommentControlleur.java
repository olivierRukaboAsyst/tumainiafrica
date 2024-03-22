package anubis.lab.anubisproject.features.comment.controller;

import java.util.List;

import anubis.lab.anubisproject.exceptions.ErrorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.comment.service.CommentService;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentControlleur {

    private CommentService commentService;

    @GetMapping("/{idComment}")
    public ResponseEntity<?> getComment(@PathVariable Long idComment) {
        try {
            return ResponseEntity.ok(commentService.getComment(idComment));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }

    }
    @GetMapping
    public ResponseEntity<?> getAllComments() {
        try {
            List<CommentDTO> commentDTOs = commentService.getAllComments();
            return ResponseEntity.ok(commentDTOs);
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }
    @PostMapping()
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @RequestParam(value = "article") Long idArticle, @RequestParam(value = "customer") String idCustomer) {
        try {
            return ResponseEntity.ok(commentService.addComment(comment, idArticle, idCustomer));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idComment}")
    public ResponseEntity<?> updateComment(@PathVariable Long idComment, @RequestParam(value = "article") Long idArticle, @RequestParam(value = "customer") String idCustomer,
            @RequestBody Comment comment) {
        try {
            return ResponseEntity.ok(commentService.updateComment(idComment, idArticle, idCustomer, comment));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable Long idComment) {
        try {
            return ResponseEntity.ok(commentService.deleteComment(idComment));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

}