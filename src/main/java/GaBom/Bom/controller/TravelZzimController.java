package GaBom.Bom.controller;

import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.service.TravelLikeService;
import GaBom.Bom.service.TravelService;
//import GaBom.Bom.service.ZzimService;
import GaBom.Bom.service.ZzimService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@RestControllerAdvice
@Transactional
@Slf4j
@RequestMapping("/api/travel/zzim")
@CrossOrigin
public class TravelZzimController {

    private final TravelLikeService travelLikeService;
    private final ZzimService zzimService;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        System.out.println("Error in ZzimController = " + e);//log로찍기.
    }

    @PostMapping("/{travelId}")
    public String ZzimTravel(@PathVariable Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        log.info("travel zzim");
        if(zzimService.ZzimTravel(travelId)){
            return "zzim_put_success";
        }else{
            return "zzim_put_failed";
        }
    }

    @PostMapping("/cancel/{travelId}")
    public String CancelZzimTravel(@PathVariable Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        log.info("travel zzim cancel");
        if(zzimService.CancelZzimTravel(travelId)){
            return "zzim_canceled";
        }else{
            return "zzim_canceled fail";
        }
    }



}
