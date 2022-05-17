package GaBom.Bom.service;

import GaBom.Bom.dto.GetTravelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
@Transactional
@Slf4j
@RequestMapping("/api/travellist")
public class TravelListController {

    private final TravelService travelService;

    @GetMapping("/update")
    public Page<GetTravelDto> Updatelist(@PageableDefault(size = 12, sort ="updateDate",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }

    @GetMapping("/like")
    public Page<GetTravelDto> Likelist(@PageableDefault(size = 12, sort ="likedCount",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }

    // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게잠 시.

//
    @GetMapping("/{userId}/my_list")
    public List<GetTravelDto> viewMyTravelList(@PathVariable String userId){ //userId : 닉네임
        List<GetTravelDto> lists = travelService.MyTravelsByUser(userId);
        return lists;
    }
    @GetMapping("/{userId}/like_list")
    public List<GetTravelDto> viewMyLikeList(@PathVariable String userId){ //userId : 닉네임
        List<GetTravelDto> lists = travelService.MyLikeTravels(userId);
        return lists;
    }


}
