package anubis.lab.anubisproject.features.tag.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TagDTO {

    private Long idTag;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
}
