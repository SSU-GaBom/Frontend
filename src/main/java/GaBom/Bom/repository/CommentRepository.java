package GaBom.Bom.repository;


import GaBom.Bom.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    void deleteByCommentId(Long commentId);
}
