package anubis.lab.anubisproject.features.comment.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private String content;
    private String createdAt;
    private String updatedAt;
}
