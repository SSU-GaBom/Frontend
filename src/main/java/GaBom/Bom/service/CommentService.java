package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CTravelNotFoundException;
import GaBom.Bom.dto.CommentDto;
import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.repository.CommentRepository;
import GaBom.Bom.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;

    @Transactional
    public boolean CreateComment(CommentDto commentDto, Long travelId) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        if(loginId=="anonymousUser") return false;

        Comment comment = commentDto.toEntity(loginId);
        travel.add(comment);
        commentRepository.save(comment);
        return true;
    }

    @Transactional
    public void deleteComment(Long commentId) { commentRepository.deleteByCommentId(commentId);
    }
}
