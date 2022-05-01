package GaBom.Bom.controller;

import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.dto.UpdateTravelDto;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.service.TravelService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RestControllerAdvice
//@Transactional
@Slf4j
public class TravelController {

    private final TravelService travelService;
    // 예외 핸들러

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        System.out.println("Error in TravelController = " + e);//log로찍기.
    }

//    @PostMapping("")
//    public String writeTravel(@RequestBody TravelDto travelDto) { //로그인세션에 맞춰서 Travel 쓰도록 하기
//        String name = "사사사"; //name이 중복되거나 없으면 안되네
//        travelService.save(name, travelDto);
//        return "write";
//    }
@PostMapping("")
public String writeTravel(@RequestBody TravelDto travelDto) { //로그인세션에 맞춰서 Travel 쓰도록 하기
//    String name = "사사사"; //name이 중복되거나 없으면 안되네
    travelService.joinTravel(travelDto);
    return "write";
}

    @GetMapping("/list/like")
    //현재는 그냥 사이즈 3으로 하고 LikeCount 순으로 정렬
    @ResponseBody
    public Page<Travel> TravelPageByLikeCount(){
        Page<Travel> page= travelService.TravelsByLikeCount(0,3);
        return page;
    }
//    @GetMapping("/list")
//    @ResponseBody
//    public Page<Travel> GetTravelPage(Pageable pageable){
//        return travelService.FindTravelsBypageRequest(pageable);
//    }

//    @GetMapping("")
//    @ResponseBody
//    public List<Travel> GetBookList_Findall(Pageable pageable){
//        return travelService.FindTravels();
//    }

    @GetMapping("/{travelId}") // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게 잠시.
    public Travel Travel_info(@PathVariable Long travelId){ //string으로 들어오나?
        Travel travel = travelService.travel_info(travelId);
        return travel; //.getTitle();
    }

    @PostMapping("/update")
    //@ResponseBody란? , id를 받아야하나?
    public String updateBoard(
            @RequestBody UpdateTravelDto updateTravelDto
    ){
        if(travelService.updateTravel(updateTravelDto)){
            return "success";
        }
        return "fail";
    }


    @GetMapping("/zzim/{userId}")
    public void func(){

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
