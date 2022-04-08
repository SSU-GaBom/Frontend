package GaBom.Bom.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/api/hi")
    public String hi() {
        return "hello";
    }

    @PostMapping("/api/test")
    public String test(@RequestBody Test test){
        testService.save(test);
        return "success";
    }

    @PostMapping("/join")
    public String joinUserTest(@RequestBody TestDto testDto){
        log.info("joinUserTest");
        Test test = testDto.toEntity();
        testService.save(test);
        return "JoinUserTest Success";
    }
}
