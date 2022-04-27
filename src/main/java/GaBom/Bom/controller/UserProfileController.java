package GaBom.Bom.controller;

import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.FollowService;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.UserProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final FollowService followService;

    @ApiOperation(value = "회원 보여주기", notes = "마이 페이지에서 회원 정보를 보여준다.")
    @GetMapping("/{nick_name}")
    public SingleResult getUserInfo(@PathVariable(name = "nick_name") String nickName){
        return userProfileService.showInfo(nickName);
    }

    @ApiOperation(value = "회원 수정", notes = "프로필 이미지를 수정한다.")
    @CrossOrigin("http://localhost:8081")
    @PutMapping(value = "/update-profile/{nick_name}")
    public SingleResult<User> updateProfile(
            @PathVariable(name = "nick_name") String nickName,
            @RequestParam(name = "profile_image") MultipartFile profileImage) {

        return userProfileService.updateProfile(nickName, profileImage);
    }

    //나를 팔로우하고 있는 사람들 전체 출력, 여기는 프론트에서 로그인 되어있지 않으면 팔로우 버튼 활성화 x
    @GetMapping("/follow/{profile-nick-name}/follower")
    public SingleResult showFollower(@PathVariable(name = "profile-nick-name") String profileNickName) throws JsonProcessingException {
        return followService.getFollower(profileNickName);
    }

    //내가 팔로우하고 있는 사람 전체 출력
    @GetMapping("/follow/{profile-nick-name}/following")
    public SingleResult showFollowing(@PathVariable(name = "profile-nick-name") String profileNickName){
        return followService.getFollowing(profileNickName);
    }

    //팔로우 버튼 눌렀을 때
    @PostMapping("/follow/{to-nick-name}")
    public SingleResult followUser(@PathVariable(name = "to-nick-name") String toNickName){
        followService.save(toNickName);
        return followService.increase(toNickName);
    }

    //언팔로우 버튼 눌렀을 때
    @DeleteMapping("/follow/{to-nick-name}")
    public SingleResult unFollowUser(@PathVariable(name = "to-nick-name") String toNickName){
        followService.deleteFollow(toNickName);
        return followService.decrease(toNickName);
    }
}
