package GaBom.Bom.controller;

import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.UserRepository;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserRepository userRepository; // Jpa를 활용한 CRUD 쿼리 가능
    private final ResponseService responseService; // 결과를 처리하는 Service
    private final UserProfileService userProfileService;
    private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);

    @ApiOperation(value = "회원 보여주기", notes = "마이 페이지에서 회원 정보를 보여준다.")
    @GetMapping("/{user_id}")
    public SingleResult getUserInfo(@PathVariable(name = "user_id") String userId, Authentication authentication){
        boolean modifiable = false;
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println(userId);
        log.debug(""+userDetails.getAuthorities());
        userProfileService.showInfo(userId, authentication.getName());
        return responseService.getSingleResult(userProfileService);
    }

    @ApiOperation(value = "회원 수정", notes = "프로필 이미지를 제외한 회원정보를 수정한다.")
    @CrossOrigin("http://localhost:8080")
    @PutMapping(value = "/update-profile/{user_id}")
    public SingleResult<User> updateProfile(
            @PathVariable(name = "user_id") String userId,
                                            Authentication authentication) {

        userProfileService.updateProfile(userId, authentication);

        return responseService.getSingleResult(userRepository.save(new User()));
    }
}
