package anubis.lab.anubisproject.features.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anubis.lab.anubisproject.features.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
