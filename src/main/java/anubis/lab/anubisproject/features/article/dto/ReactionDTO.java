package anubis.lab.anubisproject.features.article.dto;

import java.util.List;

public class ReactionDTO {
    private Long id;
    private int likeCount;
    private int laugh;
    private int dislike;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLike() {
        return likeCount;
    }

    public void setLike(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLaugh() {
        return laugh;
    }

    public void setLaugh(int laugh) {
        this.laugh = laugh;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
