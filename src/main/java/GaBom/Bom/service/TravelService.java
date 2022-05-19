package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CTravelNotFoundException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.*;
import GaBom.Bom.entity.*;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
//    private final TravelFileHandler travelFileHandler;
    private final PinRepository pinRepository;


    private final TravelImageService travelImageService;

    private final UserProfileService userProfileService;
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    @Transactional
    public void joinTravel (TravelDto travelDto) {
        System.out.println("joinStart");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        // TODO :: 만약 이거 없으면 뭐나옴? ->예외처리 물어보기

        Date time = new Date();
        String localTime = format.format(time);
        List<Pin> pinList = travelDto.getPinList();

        Travel build = Travel.builder()
                .travelId(travelDto.getTravelId())
                .myuser(userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new))//이거 고쳐야함
                .pinList(travelDto.getPinList())
                .title(travelDto.getTitle())
                .city(travelDto.getCity())
                .state(travelDto.getState())
                .appendDate(localTime)
                .updateDate(localTime)
                .startDate(travelDto.getStartDate())
                .endDate(travelDto.getEndDate())
                .expense(travelDto.getExpense())
                .content(travelDto.getContent())
                .isShared(false)
                .likedCount(0)
                .zzimCount(0)
                .transportation(travelDto.getTransportation())
                .build();

        for (Pin pin : pinList) {
            List<TravelImage> travelImages= pin.getImages();
            try {
                travelImageService.createPin(pin , travelImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("here2");
            locationRepository.save(pin.getLocation()); //location이 안들어올때 에러처리 해야함 TODO
            //?? 이거 안해도 저장이 되는구나
            pin.setTravel(build);
            pinRepository.save(pin);
            log.info("here3");
        }
        log.info("here4");
        travelRepository.save(build);
    }


    @Transactional
    public Travel travel_info(Long travelId) {
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);
        initHibernate(travel);
        return travel;
    }



    //게시글 수정
    @Transactional
    public boolean updateTravel(UpdateTravelDto updateTravelDto){
        Travel travel = travelRepository.findByTravelId(updateTravelDto.getTravelId()).orElseThrow(CTravelNotFoundException::new);
        travel.updateTravel(updateTravelDto);
        return true;
    }

//    @Transactional
//    public List<GetTravelDto> TravelsByUpdateTime(){
//        List<Travel> travels =travelRepository.findAll();
//        List<GetTravelDto> lists= new ArrayList<>();
//        for (Travel travel : travels) {
//            List<Pin> pinList = travel.getPinList();
//            Hibernate.initialize(pinList); //정보확인
//            for (Pin pin : pinList) {
//                Hibernate.initialize(pin.getImages());
//            }
//            Hibernate.initialize(travel.getPinList());
//            //lazy
//            lists.add(new GetTravelDto(travel));
//        }
//        return lists;
//    }


    @Transactional
    public List<GetTravelDto> MyTravelsByUser(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        List<Travel> myTravelList = user.getMyTravelList();
        List<GetTravelDto> lists= new ArrayList<>();
        for (Travel travel : myTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
            }
            Hibernate.initialize(travel.getPinList());
            //lazy
            lists.add(new GetTravelDto(travel));
        }

        return lists;
    }

    @Transactional
    public List<GetTravelDto> MyLikeTravels(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        List<Travel> LikeTravelList = user.getLikedTravelList();
        System.out.println(" 좋아요 list 출력");
        for (Travel travel : LikeTravelList) {
            System.out.println("travel.getTitle() = " + travel.getTitle());
        }
        //없으면??
        List<GetTravelDto> lists= new ArrayList<>();
        for (Travel travel : LikeTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
            }
            Hibernate.initialize(travel.getPinList());
            lists.add(new GetTravelDto(travel));
        }
        return lists;
    }

    @Transactional
    public List<GetTravelDto> MyZzimTravels(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        List<Travel> LikeTravelList = user.getZzimTravelList();
        System.out.println(" 찜 list 출력");
        for (Travel travel : LikeTravelList) {
            System.out.println("travel.getTitle() = " + travel.getTitle());
        }
        //없으면??
        List<GetTravelDto> lists= new ArrayList<>();
        for (Travel travel : LikeTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
            }
            Hibernate.initialize(travel.getPinList());
            lists.add(new GetTravelDto(travel));
        }
        return lists;
    }


    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

    @Transactional
    public Page<GetTravelDto> getTravelDtopages(Pageable pageable) {
        Page<Travel> page = travelRepository.findAll(pageable);
        for (Travel travel : page) {
            initHibernate(travel);
        }
        Page<GetTravelDto> travels = page.map(GetTravelDto::new);
        return travels;
    }


    //Json으로 Lazy로 안딸려나와서 만든 함수. 이렇게 하는게 맞나?
    void initHibernate(Travel travel){
        List<Pin> pinList = travel.getPinList();
        Hibernate.initialize(pinList); //정보확인
        List<Comment> commentList = travel.getCommentList();
        Hibernate.initialize(pinList); //정보확인
        for (Comment comment : commentList) {
            Hibernate.initialize(comment); //정보확인
        }
        for (Pin pin : pinList) {
            Hibernate.initialize(pin.getImages());
        }
        Hibernate.initialize(travel.getPinList());
    }

    //==비즈니스 로직==//

}
