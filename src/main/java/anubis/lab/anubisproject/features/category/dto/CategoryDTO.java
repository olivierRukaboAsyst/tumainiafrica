package anubis.lab.anubisproject.features.category.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private String iconUrl;
    private String description;
    private String createdAt;
    private String updatedAt;
}
