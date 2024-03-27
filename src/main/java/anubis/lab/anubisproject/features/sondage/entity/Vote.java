package anubis.lab.anubisproject.features.sondage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Vote {
    private Long id;
    @OneToMany(mappedBy = "vote")
    private List<Candidate> candidates;
}
