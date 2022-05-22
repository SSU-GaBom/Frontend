package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CTravelNotFoundException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.CommentDto;
import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.CommentRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;

    private final UserRepository userRepository;

    @Transactional
    public boolean CreateComment(CommentDto commentDto, Long travelId) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        if(loginId=="anonymousUser") {
            log.info("session is ended");
            return false;
        }
        User user = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        String nickname = user.getNickName();

        Comment comment = commentDto.toEntity(nickname);
        travel.add(comment);
        commentRepository.save(comment);

        return true;
    }


//    @Transactional
//    public Page<CommentDto> GetComments(Long travelId, Pageable pageable) { //로그인세션에 맞춰서 Travel 쓰도록 하기
//        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
//        CommentDto commentDto = new CommentDto();
//        Comment comment = commentDto.toEntity(nickname);
//        travel.add(comment);
//        commentRepository.save(comment);
//
//        return true;
//    }



    @Transactional
    public void deleteComment(Long commentId) { commentRepository.deleteByCommentId(commentId);
    }
}
