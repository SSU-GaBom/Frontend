package GaBom.Bom.controller;

import GaBom.Bom.entity.User;
import GaBom.Bom.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /*
    @GetMapping("/{user_id}")
    public ResponseEntity getUserInfo(@PathVariable(name = "user_id") String userId){
        User user = userInfoService.showInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

     */
}
