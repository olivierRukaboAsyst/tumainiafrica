package anubis.lab.anubisproject.features.comment.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CommentDTO {

    private int id;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
