package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CCommentNotFoundException;
import GaBom.Bom.advice.exception.CTravelNotFoundException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.FileHandler;
import GaBom.Bom.dto.CommentDto;
import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.dto.RankingZzimDto;
import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.CommentRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;

    private final FileHandler fileHandler;

    private final UserRepository userRepository;

    @Transactional
    public boolean CreateComment(CommentDto commentDto, Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Date time = new Date();
        String localTime = format.format(time);

        log.info("댓글만듬");
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
        User user = getUser();
        if (user == null) return false;
        log.info("댓글만듬2");
        String nickname = user.getNickName();
        commentDto.setUpdateDate(localTime);
        commentDto.setNickname(nickname);
        Comment comment = commentDto.toEntity();
        travel.add(comment);
        log.info("댓글만듬3");
        commentRepository.save(comment);

        return true;
    }




//    @Transactional
//    public Page<CommentDto> GetComments(Long travelId, Pageable pageable) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
//        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
//        Page<Comment> comments = commentRepository.findByTravel(travel,pageable);
//
//        User Comeuser = getUser();
//
//        Page<CommentDto> commentdtos = comments.map(CommentDto::new);
//        for (CommentDto commentdto : commentdtos) {
//            User user = userRepository.findByNickName(commentdto.getNickname()).orElseThrow(CUserNotFoundException::new);
//            if(Comeuser==user) //내가 쓴 글일때
//                commentdto.setIsMyComment(true);
//            if(user.getProfileImage()!=null)
//            {
//                byte[] profileImageByte = fileHandler.getProfileImageByte(user.getProfileImage());
//                commentdto.setProfileImage(profileImageByte);
//            }
//        }
////        return commentdtos;
//
//                // ArrayList 준비
//
//        List<Comment> commentList1 = travel.getCommentList();
//
//        ArrayList<Comment> commentList = (ArrayList<Comment>) travel.getCommentList();
////        //comment -> comentdto
//        List<CommentDto> commentdtolists = new ArrayList<>();
//        for (Comment commentlist : commentList) {
//            commentdtolists.add(commentlist.toDto());
//        }
//        commentdtolists = (ArrayList<CommentDto>) commentdtolists.stream().sorted(Comparator.comparing(CommentDto::getUpdateDate).reversed()).collect(Collectors.toList());
//
//        return commentdtolists
//                // price순 내림차순으로 정렬
//        return commentdtos;
//    }


    @Transactional
    public List<CommentDto> GetCommentLists(Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        log.info("comment list start");
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
        User Comeuser = getUser();
        log.info("User Get");

        List<Comment> commentList1 = travel.getCommentList();

        log.info("Comment Lists Get");
        List<CommentDto> commentdtolists = new ArrayList<>();
        for (Comment commentlist : commentList1) {
            commentdtolists.add(commentlist.toDto());
        }
        log.info("CommentDto Lists Get");

        for (CommentDto commentdto : commentdtolists) {
            User user = userRepository.findByNickName(commentdto.getNickname()).orElseThrow(CUserNotFoundException::new);
            if(Comeuser==user) //내가 쓴 글일때
                commentdto.setIsMyComment(true);
            if(user.getProfileImage()!=null)
            {
                byte[] profileImageByte = fileHandler.getProfileImageByte(user.getProfileImage());
                commentdto.setProfileImage(profileImageByte);
            }
        }
        log.info("CommentDto Lists images Get");

        commentdtolists =commentdtolists.stream().sorted(Comparator.comparing(CommentDto::getUpdateDate).reversed()).collect(Collectors.toList());
        log.info("CommentDto Lists sort Get");
        return commentdtolists;
    }









    @Transactional
    public boolean deleteComment(Long commentId) {
        User user= getUser();
        Comment comment1 = commentRepository.findByCommentId(commentId).orElseThrow(CCommentNotFoundException::new);
        if(user.getNickName().equals(comment1.getNickname())) { //같은 경우 (지우는 사람이 같음)
            log.info("댓글이 삭제되었습니다.");
            commentRepository.deleteByCommentId(commentId);
            return true;
        }
        log.info("댓글을 삭제할 수 없습니다. 아이디가 다릅니다.");
        return false;
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        if(loginId=="anonymousUser") {
            log.info("session is ended");
            return null;
        }
        User user = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        return user;
    }
}
