package anubis.lab.anubisproject.features.tag.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TagDTO {

    private int idTag;
    private String name;
    private String description;
    private String iconUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
