package GaBom.Bom;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
