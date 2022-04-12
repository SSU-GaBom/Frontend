package GaBom.Bom.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/api/hi")
    public String hi() {
        return "hello";
    }

    @GetMapping("/api/testApi/{userId}")
    public String testApi(@PathVariable String userId){
        log.info("testApi : {}" , userId );
        return "testSuccess";
    }

    @PostMapping("/api/test")
    public String test(@RequestBody Test test){
        testService.save(test);
        return "success";
    }

    @PostMapping("/api/join")
    public String joinUserTest(@RequestBody TestDto testDto){
        log.info("joinUserTest");
        Test test = testDto.toEntity();
        testService.save(test);
        return "JoinUserTest Success";
    }

    /*
    @GetMapping("/api/checkId")
    public String checkIdTest(@RequestParam String userId){
        log.info("checkId : {}", userId );
        return "확인했습니다.";
    }

     */
}
