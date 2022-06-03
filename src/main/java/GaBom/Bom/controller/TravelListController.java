package GaBom.Bom.controller;

import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
@Transactional
@Slf4j
@RequestMapping("/api/travellist")
@CrossOrigin
public class TravelListController {

    private final TravelService travelService;

    @GetMapping("/update")
    public Page<GetTravelDto> Updatelist(
            @PageableDefault(size = 12, sort = "updateDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }

    @GetMapping("/like")
    public Page<GetTravelDto> Likelist(
            @PageableDefault(size = 12, sort = "likedCount", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }

    // 제목 검색해보기
    @GetMapping("/find/{title}")
    public Page<GetTravelDto> FindTitles(@PathVariable String title,
            @PageableDefault(size = 4, sort = "likedCount", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> titlePages = travelService.getTitlePages(title, pageable);
        System.out.println("titlePages.getContent() = " + titlePages.getContent());
        System.out.println("titlePages.getTotalPages() = " + titlePages.getTotalPages());
        return titlePages;
    }

    // Travel 자세한 내용 리턴. 지금은 그냥 Title 리턴하게잠 시.

    //
    @GetMapping("/{userId}/my_list")
    public List<GetTravelDto> viewMyTravelList(@PathVariable String userId) { // userId : 닉네임
        List<GetTravelDto> lists = travelService.MyTravelsByUser(userId);
        return lists;
    }

    @GetMapping("/{userId}/like_list")
    public List<GetTravelDto> viewMyLikeList(@PathVariable String userId) { // userId : 닉네임
        List<GetTravelDto> lists = travelService.MyLikeTravels(userId);
        return lists;
    }

    @GetMapping("/{userId}/zzim_list")
    public List<GetTravelDto> viewMyZzimikeList(@PathVariable String userId) { // userId : 닉네임
        List<GetTravelDto> lists = travelService.MyZzimTravels(userId);
        return lists;
    }

}
