package GaBom.Bom.controller;


import GaBom.Bom.dto.UpdateTravelDto;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
@RestControllerAdvice
//@Transactional
public class TravelController {

    private final TravelService travelService;
    // 예외 핸들러

    @ExceptionHandler(Exception.class)
    public void exceptinoHandler(Exception e) {
        System.out.println("Error in TravelController = " + e);//log로찍기.
    }

    @PostMapping("")
    public String writeTravel(@RequestBody Travel travel) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        String name = "사사사"; //name이 중복되거나 없으면 안되네
        travelService.save(name, travel);
        return "write";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Travel> GetBookList(Pageable pageable){
        return travelService.FindTravelsBypageRequest(pageable);
    }

    @GetMapping("")
    @ResponseBody
    public List<Travel> GetBookList_Findall(Pageable pageable){
        return travelService.FindTravels();
    }

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
        deleteTravel(travelId);
        return "deleted";
    }



}
