package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNoRelationException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.FollowRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ResponseService responseService;

    @Transactional
    public Long getFollowUserIdByFromUserIdToUserId(String fromUserId, String toUserId){
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser).orElseThrow(CNoRelationException::new);

        return follow.getId();
    }

    @Transactional
    public SingleResult save(String fromUserId, String toUserId){
        User fromUser = userRepository.findByUserId(fromUserId).orElseThrow(CUserNotFoundException::new);
        User toUser = userRepository.findByUserId(toUserId).orElseThrow(CUserNotFoundException::new);

        return responseService.getSingleResult(followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build()));
    }
}
