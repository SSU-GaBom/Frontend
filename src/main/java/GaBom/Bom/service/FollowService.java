package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNoRelationException;
import GaBom.Bom.advice.exception.CRelationAlreadyExistsException;
import GaBom.Bom.advice.exception.CSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.dto.FollowDto;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.ListResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.FollowRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.qlrm.mapper.JpaResultMapper;
import javax.persistence.EntityManager;
import javax.persistence.InheritanceType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ResponseService responseService;
    private final EntityManager em;

    //언팔하게 되면 상대방에게 본인을 팔로우하는 사람이 줄기 때문에 해당 숫자를 리턴
    @Transactional
    public SingleResult decrease(String toNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fromUserId = authentication.getName();

        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByNickName(toNickName).orElseThrow(CUserNotFoundException::new);

        fromUser.setFollowingNum(fromUser.getFollowingNum() - 1);
        toUser.setFollwerNum(toUser.getFollwerNum() - 1);

        return responseService.getSingleResult(toUser.getFollwerNum());
    }

    //팔로우 하게 되면 상대방에게 본인을 팔로우하는 사람이 늘기 때문에 해당 숫자를 리턴
    @Transactional
    public SingleResult increase(String toNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fromUserId = authentication.getName();

        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByNickName(toNickName).orElseThrow(CUserNotFoundException::new);

        fromUser.setFollowingNum(fromUser.getFollowingNum() + 1);
        toUser.setFollwerNum(toUser.getFollwerNum() + 1);

        return responseService.getSingleResult(toUser.getFollwerNum());
    }

    @Transactional
    public void save(String toNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fromUserId = authentication.getName();

        User toUser = userRepository.findByNickName(toNickName).orElseThrow(CUserNotFoundException::new);
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);

        String toUserId = toUser.getUserId();

        //save 하기 전에 유저가 같은 경우라면 에러 내뿜고 종료
        if(toUserId.equals(fromUserId))
            throw new CSameUserException();
        //관계가 이미 형성 되어있으면 에러
        if(followRepository.existsByFromUserAndToUser(fromUser, toUser))
            throw new CRelationAlreadyExistsException();

        log.info("from:"+fromUserId + " to:"+toUserId);

        //팔로우를 만들어낸 후 저장
        followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build());
    }

    @Transactional
    public void deleteFollow(String toNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fromUserId = authentication.getName();

        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByNickName(toNickName).orElseThrow(CUserNotFoundException::new);

        String toUserId = toUser.getUserId();

        if(toUserId.equals(fromUserId))
            throw new CSameUserException();

        //관계가 형성되어 있지 않으면 에러
        Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser).orElseThrow(CNoRelationException::new);
        //관계를 가지고 있는 follow 테이블의 id 받아오기

        followRepository.deleteById(follow.getId());
    }

    @Transactional
    public ListResult getFollower(String profileNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        log.info(profileNickName);
        log.info(loginId);

        User profileUser = userRepository.findByNickName(profileNickName).orElseThrow(CUserNotFoundException::new);

        String profileId = profileUser.getUserId();

        if(profileId.equals(loginId))
            throw new CSameUserException();

        List<Follow> userList = followRepository.findAllByToUser(profileUser).orElseThrow(CNoRelationException::new);
        List<String> fromUserList = new ArrayList<>();

        for(Follow follow:userList){
            fromUserList.add(follow.getFromUser().getNickName());
            log.info(follow.getFromUser().getNickName());
        }

        return responseService.getListResult(fromUserList);
    }

    @Transactional
    public ListResult getFollowing(String profileNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        User profileUser = userRepository.findByNickName(profileNickName).orElseThrow(CUserNotFoundException::new);

        log.info(profileNickName);
        log.info(loginId);

        String profileId = profileUser.getUserId();

        if(profileId.equals(loginId))
            throw new CSameUserException();

        List<Follow> userList = followRepository.findAllByFromUser(profileUser).orElseThrow(CNoRelationException::new);
        List<String> toUserList = new ArrayList<>();

        for(Follow follow:userList){
            toUserList.add(follow.getToUser().getNickName());
            log.info(follow.getToUser().getNickName());
        }

        return responseService.getListResult(toUserList);
    }
}
