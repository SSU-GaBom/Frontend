package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNoRelationException;
import GaBom.Bom.advice.exception.CRelationAlreadyExistsException;
import GaBom.Bom.advice.exception.CSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.dto.FollowDto;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
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
import javax.persistence.Query;
import javax.transaction.Transactional;
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
        followRepository.existsByFromUserAndToUser(fromUser, toUser).orElseThrow(CRelationAlreadyExistsException::new);

        log.info("from:"+fromUserId + " to:"+toUserId);

        //팔로우를 만들어낸 후 저장
        followRepository.save(Follow.builder().fromUser(fromUser).toUser(toUser).build());
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
    public SingleResult getFollower(String profileNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        log.info(profileNickName);
        log.info(loginId);

        User toUser = userRepository.findByNickName(profileNickName).orElseThrow(CUserNotFoundException::new);

        String profileId = toUser.getUserId();

        if(profileId.equals(loginId))
            throw new CSameUserException();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.user_id, u.user_name, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.user_id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.user_id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.user_id = f.from_user_id AND f.to_user_id = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);

        return responseService.getSingleResult(followDtoList);
    }

    @Transactional
    public SingleResult getFollowing(String profileNickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        User toUser = userRepository.findByNickName(profileNickName).orElseThrow(CUserNotFoundException::new);

        String profileId = toUser.getUserId();

        if(profileId.equals(loginId))
            throw new CSameUserException();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.user_id, u.name, u.profile_img_url, ");
        sb.append("if ((SELECT 1 FROM follow WHERE from_user_id = ? AND to_user_id = u.id), TRUE, FALSE) AS followState, ");
        sb.append("if ((?=u.id), TRUE, FALSE) AS loginUser ");
        sb.append("FROM user u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, loginId)
                .setParameter(3, profileId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);

        return responseService.getSingleResult(followDtoList);
    }
}
