package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNoRelationException;
import GaBom.Bom.advice.exception.CRelationAlreadyExistsException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.FollowRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ResponseService responseService;

    @Transactional
    public SingleResult decrease(String fromUserId, String toUserId){
        boolean unfollow = false;
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        fromUser.setFollowingNum(fromUser.getFollowingNum() - 1);
        toUser.setFollwerNum(toUser.getFollwerNum() - 1);

        unfollow = true;
        return responseService.getSingleResult(unfollow);
    }

    @Transactional
    public SingleResult increase(String fromUserId, String toUserId){
        boolean follow = false;
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        fromUser.setFollowingNum(fromUser.getFollowingNum() + 1);
        toUser.setFollwerNum(toUser.getFollwerNum() + 1);

        follow = true;
        return responseService.getSingleResult(follow);
    }

    @Transactional
    public Long getFollowUserIdByFromUserIdToUserId(String fromUserId, String toUserId){
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        //관계가 형성되어 있지 않으면 에러
        Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser).orElseThrow(CNoRelationException::new);

        //관계를 가지고 있는 follow 테이블의 id 받아오기
        return follow.getId();
    }

    @Transactional
    public void save(String fromUserId, String toUserId){
        boolean follow = false;
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        log.info("from:"+fromUserId + " to:"+toUserId);

        //관계가 이미 형성 되어있으면 에러
        if(followRepository.existsByFromUserAndToUser(fromUser, toUser))
            throw new CRelationAlreadyExistsException();

        follow = true;

        //팔로우를 만들어낸 후 저장
        followRepository.save(Follow.builder().fromUser(fromUser).toUser(toUser).build());
    }
}
