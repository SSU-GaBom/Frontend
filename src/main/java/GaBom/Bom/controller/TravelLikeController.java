package GaBom.Bom.controller;

import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.service.TravelLikeService;
import GaBom.Bom.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestControllerAdvice
//@Transactional
@Slf4j
@RequestMapping("/api/travel/like")
public class TravelLikeController {

    private final TravelLikeService travelLikeService;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        System.out.println("Error in TravelLikeController = " + e);//log로찍기.
    }

    @PostMapping("/{travelId}")
    public String LikeTravel(@PathVariable Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        log.info("travel like");
        if(travelLikeService.LikeTravel(travelId)){
            return "like_put_success";
        }else{
            return "like_put_failed";
        }
    }

    @PostMapping("/cancel/{travelId}")
    public String CancelLikeTravel(@PathVariable Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        log.info("travel like cancel");
        if(travelLikeService.CancelLikeTravel(travelId)){
            return "like_canceled";
        }else{
            return "like_canceled fail";
        }
    }

}
