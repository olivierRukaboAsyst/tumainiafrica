package anubis.lab.anubisproject.features.category.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CategoryDTO {

    private int id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
