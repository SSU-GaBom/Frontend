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
        log.info("ranking start");
        RankingDto rankingDto = new RankingDto();
        try {


            List<RankingRecommendDto> rankingRecommendDtos = new ArrayList<>();
            List<RankingZzimDto> rankingZzimDto = new ArrayList<>();
            List<RankingFollowDto> rankingFollowDtos = new ArrayList<>();
            //정렬
            log.info("extract Recommend");

            extractedRecommend(rankingRecommendDtos);

            log.info("extract Zzim");

            Pageable sortedByzzimcount =
                    PageRequest.of(0, 10,  Sort.by(Sort.Direction.DESC,"zzimCount"));
//                    PageRequest.of(0, 10, Sort.by("zzimCount").descending());
            Page<Travel> Zzim_travel = travelRepository.findAll(sortedByzzimcount);
            List<Travel> tmptravels = Zzim_travel.getContent();
            for (Travel tmptravel : tmptravels) {
                rankingZzimDto.add(new RankingZzimDto(tmptravel));
            }
            log.info("extract Follower");


            extractedFollwers(rankingFollowDtos);
            //travel -> like순으로 돌리기
            rankingDto.setRankingRecommendDtos(rankingRecommendDtos);
            rankingDto.setRankingZzimDtos(rankingZzimDto);
            rankingDto.setRankingFollowDto(rankingFollowDtos);

            log.info("Ranking end");
            return rankingDto;
        }catch (Exception e){
            e.printStackTrace();
        }
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
        log.info("recommend 1");
        Pageable sortedByLikeCount =
                PageRequest.of(0, 10,  Sort.by(Sort.Direction.DESC,"likedCount"));
//                PageRequest.of(0, 10,  Sort.by("likeCount").descending());
        log.info("recommend 2");
        try {
            Page<Travel> Like_travels = travelRepository.findAll(sortedByLikeCount);
            log.info("recommend 3");
            List<Travel> tmptravels = Like_travels.getContent();
            log.info("recommend 5");

            for (Travel tmptravel : tmptravels) {
                rankingRecommendDtos.add(new RankingRecommendDto(tmptravel));
            }
            log.info("recommend 4");
        }catch(Exception e){
            e.printStackTrace();
        }
//        log.info("recommend 3");
//        List<Travel> tmptravels = Like_travels.getContent();
//        log.info("recommend 5");
//
//        for (Travel tmptravel : tmptravels) {
//            rankingRecommendDtos.add(new RankingRecommendDto(tmptravel));
//        }
//        log.info("recommend 4");



    }

    private void extractedZzim(List<RankingRecommendDto> rankingRecommendDtos) {
        System.out.println(" 1!");
        Pageable sortedByLikeCount =
                PageRequest.of(0, 10,  Sort.by("likeCount").descending());
        System.out.println(" 2!");

        Page<Travel> Like_travels =  travelRepository.findAll(sortedByLikeCount);
        System.out.println(" 3!");

        List<Travel> tmptravels = Like_travels.getContent();
        for (Travel tmptravel : tmptravels) {
            rankingRecommendDtos.add(new RankingRecommendDto(tmptravel));
        }
        System.out.println(" 4!");

    }



    @GetMapping("/like")
    public Page<GetTravelDto> Likelist(@PageableDefault(size = 12, sort ="likedCount",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetTravelDto> travels = travelService.getTravelDtopages(pageable);
        return travels;
    }
}
