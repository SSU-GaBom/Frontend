package GaBom.Bom.controller;

import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.dto.UpdateTravelDto;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.repository.TravelRepository;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//@RestController
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
    @Transactional
    @GetMapping("/{travelId}")
    public GetTravelDto Travel_info(@PathVariable Long travelId){
        Travel travel = travelService.travel_info(travelId);
        GetTravelDto getTravelDto = new GetTravelDto(travel);
        return getTravelDto;
    }


    //최신순 travellist 모두 Page는 기본 12임.
//    @Transactional
//    @GetMapping("/updatelist")
//    public Page<GetTravelDto> Updatelist(@PageableDefault(size = 12, sort ="updateDate",
//            direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
//        return travels;
//    }
//
//    @Transactional
//    @GetMapping("/likelist")
//    public Page<GetTravelDto> Likelist(@PageableDefault(size = 12, sort ="likedCount",
//            direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
//        return travels;
//    }
//
//    // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게잠 시.
//
//
//    @Transactional
//    @GetMapping("/{userId}/my_list")
//    public List<GetTravelDto> viewMyTravelList(@PathVariable String userId){ //userId : 닉네임
//        List<GetTravelDto> lists = travelService.TravelsByUser(userId);
//        return lists;
//    }
//    @Transactional
//    @GetMapping("/{userId}/like_list")
//    public List<GetTravelDto> viewMyTravelList(@PathVariable String userId){ //userId : 닉네임
//        List<GetTravelDto> lists = travelService.TravelsByUser(userId);
//        return lists;
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
