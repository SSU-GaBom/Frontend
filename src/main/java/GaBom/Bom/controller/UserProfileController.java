package GaBom.Bom.controller;

import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.FollowRepository;
import GaBom.Bom.repository.UserRepository;
import GaBom.Bom.service.FollowService;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserRepository userRepository; // Jpa를 활용한 CRUD 쿼리 가능
    private final ResponseService responseService; // 결과를 처리하는 Service
    private final UserProfileService userProfileService;
    private final JwtTokenProvider jwtTokenProvider;
    private final FollowService followService;
    private final FollowRepository followRepository;

    //isme 변수를 넘겨 받은 프론트 페이지에서는 수정 가능하다. -> 여기서 리턴한 isme 변수에 따라서
    @ApiOperation(value = "회원 보여주기", notes = "마이 페이지에서 회원 정보를 보여준다.")
    @GetMapping("/{user_id}")
    public SingleResult getUserInfo(@PathVariable(name = "user_id") String userId,
                                    @RequestParam String token){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        return userProfileService.showInfo(userId, authentication.getName());
    }

    @ApiOperation(value = "회원 수정", notes = "프로필 이미지를 제외한 회원정보를 수정한다.")
    @CrossOrigin("http://localhost:8081")
    @PutMapping(value = "/update-profile/{user_id}")
    public SingleResult<User> updateProfile(
            @PathVariable(name = "user_id") String userId,
            @RequestParam String token,
            @RequestParam MultipartFile profileImage) {
        User user;
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if(!userId.equals(authentication.getName()))
            throw new CNotSameUserException();
        else
            user = userRepository.findByUserId(authentication.getName()).orElseThrow(CUserNotFoundException::new);

        userProfileService.updateProfile(user, profileImage);

        return responseService.getSingleResult(new User());
    }

    //나를 팔로우하고 있는 사람들 전체 출력
    @GetMapping("/follow/{toUserId}/follower")
    public SingleResult showFollower(){

    }

    //내가 팔로우하고 있는 사람 전체 출력
    @GetMapping("/follow/{toUserId}/following")
    public SingleResult showFollowing(){

    }

    //팔로우 버튼 눌렀을 때
    @PostMapping("/follow/{toUserId}")
    public SingleResult followUser(@PathVariable String toUserId,
                             @RequestParam String token){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if(toUserId.equals(authentication.getName()))
            throw new CSameUserException();
        followService.save(authentication.getName(), toUserId);

        return followService.increase(authentication.getName(), toUserId);
    }

    //언팔로우 버튼 눌렀을 때
    @DeleteMapping("/follow/{toUserId}")
    public SingleResult unFollowUser(@PathVariable String toUserId,
                             @RequestParam String token){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if(toUserId.equals(authentication.getName()))
            throw new CSameUserException();

        Long id = followService.getFollowUserIdByFromUserIdToUserId(authentication.getName(), toUserId);
        followRepository.deleteById(id);

        return followService.decrease(authentication.getName(), toUserId);
    }
}
