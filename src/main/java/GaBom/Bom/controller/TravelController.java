package GaBom.Bom.controller;

import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.dto.UpdateTravelDto;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.service.TravelService;
import GaBom.Bom.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RestControllerAdvice
//@Transactional
@Slf4j
@RequestMapping("/api/travel")
public class TravelController {
    private final TravelService travelService;
    // 예외 핸들러

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        System.out.println("Error in TravelController = " + e);//log로찍기.
    }

    @PostMapping("")
    public String writeTravel(@RequestBody TravelDto travelDto) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        travelService.joinTravel(travelDto);
        return "write"; //Return값 어떻게?
    }








    // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게 잠시.
    @GetMapping("/{travelId}")
    public GetTravelDto Travel_info(@PathVariable Long travelId){
        Travel travel = travelService.travel_info(travelId);
        GetTravelDto getTravelDto = new GetTravelDto(travel);
        return getTravelDto;
    }

//    @GetMapping("/list/like")
//    //현재는 그냥 사이즈 3으로 하고 LikeCount 순으로 정렬
//    @ResponseBody
//    public Page<GetTravelDto> TravelPageByLikeCount(){
//        Page<GetTravelDto> page= travelService.TravelsByLikeCount(0,3);
//        return page;
//    }




    @PostMapping("/update")
    public String updateBoard(
            @RequestBody UpdateTravelDto updateTravelDto
    ){
        if(travelService.updateTravel(updateTravelDto)){
            return "success";
        }
        return "fail";
    }




    @GetMapping("/recommend") //전국에서 추천 많은순 여행리스트
    public void func2(){

    }

    @GetMapping("recommend/more") //
    public void func4(){

    }
    //redirect 생각
    @DeleteMapping("/{travelId}")
    String deleteTravel(@PathVariable Long travelId) {
        travelService.deleteTravel(travelId);
        return "deleted";
    }

}
