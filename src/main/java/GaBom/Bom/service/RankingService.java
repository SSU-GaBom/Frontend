package GaBom.Bom.service;

import GaBom.Bom.dto.*;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.FollowRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RankingService {

    private final UserRepository userRepository;
    private final TravelRepository travelRepository;
    private final TravelService travelService;
    private final FollowRepository followRepository;
    private final FollowService followService;



    public RankingDto findRank(){
        RankingDto rankingDto = new RankingDto();
        List<RankingRecommendDto> rankingRecommendDtos = new ArrayList<>();
        List<RankingZzimDto> rankingZzimDto = new ArrayList<>();
        List<RankingFollowDto> rankingFollowDtos = new ArrayList<>();

        //정렬
        extractedRecommend(rankingRecommendDtos);

        Pageable sortedByZzimcount =
                PageRequest.of(0, 10,  Sort.by("zzimCount").descending());
        Page<Travel> Zzim_travel =  travelRepository.findAll(sortedByZzimcount);
        List<Travel> tmptravels = Zzim_travel.getContent();
        for (Travel tmptravel : tmptravels) {
            rankingZzimDto.add(new RankingZzimDto(tmptravel));
        }

        extractedFollwers(rankingFollowDtos);
        //travel -> like순으로 돌리기
        rankingDto.setRankingRecommendDtos(rankingRecommendDtos);
        rankingDto.setRankingZzimDtos(rankingZzimDto);
        rankingDto.setRankingFollowDto(rankingFollowDtos);

        return rankingDto;
    }

    private void extractedFollwers(List<RankingFollowDto> rankingFollowDtos) {
        Pageable sortedByFollower =
                PageRequest.of(0, 10,  Sort.by("follwerNum").descending());
        Page<User> follower_user =  userRepository.findAll(sortedByFollower);
        List<User> users = follower_user.getContent();
        for (User user : users) {
            rankingFollowDtos.add(new RankingFollowDto(user));
        }
    }

//    private void extractedZzim(List<RankingZzimDto> rankingZzimDto) {
//        Pageable sortedByZzimcount =
//                PageRequest.of(0, 10,  Sort.by("zzimCount").descending());
//        Page<Travel> Zzim_travel =  travelRepository.findAll(sortedByZzimcount);
//        List<Travel> tmptravels = Zzim_travel.getContent();
//        for (Travel tmptravel : tmptravels) {
//            rankingZzimDto.add(new RankingZzimDto(tmptravel));
//        }
//    }

    private void extractedRecommend(List<RankingRecommendDto> rankingRecommendDtos) {
        Pageable sortedByLikeCount =
                PageRequest.of(0, 10,  Sort.by("likeCount").descending());
        Page<Travel> Like_travels =  travelRepository.findAll(sortedByLikeCount);
        List<Travel> tmptravels = Like_travels.getContent();
        for (Travel tmptravel : tmptravels) {
            rankingRecommendDtos.add(new RankingRecommendDto(tmptravel));
        }
    }

    private void extractedZzim(List<RankingRecommendDto> rankingRecommendDtos) {
        Pageable sortedByLikeCount =
                PageRequest.of(0, 10,  Sort.by("likeCount").descending());
        Page<Travel> Like_travels =  travelRepository.findAll(sortedByLikeCount);
        List<Travel> tmptravels = Like_travels.getContent();
        for (Travel tmptravel : tmptravels) {
            rankingRecommendDtos.add(new RankingRecommendDto(tmptravel));
        }
    }



    @GetMapping("/like")
    public Page<GetTravelDto> Likelist(@PageableDefault(size = 12, sort ="likedCount",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }
}
