package GaBom.Bom.repository;


import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    void deleteByCommentId(Long commentId);

    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findByTravel(Travel travel,Pageable pageable);


    public ArrayList<Comment> findAllByTravel(Travel travel);

//    Comment findByCommentId(Long commentId);

    public Optional<Comment> findByCommentId(Long commentId);

    ArrayList<Comment> findByTravel(Travel travel);


//    Page<Comment> findByTravelId(Long travelId, Pageable pageable);
}
