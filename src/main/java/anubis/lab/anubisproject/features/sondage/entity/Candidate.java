package anubis.lab.anubisproject.features.sondage.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class Candidate {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    @ManyToOne()
    private Vote vote;
}
