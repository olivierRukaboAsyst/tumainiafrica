package anubis.lab.anubisproject.features.customer.entity;

import anubis.lab.anubisproject.features.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullname;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
