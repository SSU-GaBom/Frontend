package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CTravelNotFoundException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.TravelFileHandler;
import GaBom.Bom.dto.*;
import GaBom.Bom.entity.*;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
    private final TravelFileHandler travelFileHandler;
    private final PinRepository pinRepository;

    private final TravelImageService travelImageService;

    private final UserProfileService userProfileService;
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    @Transactional
    public void joinTravel (TravelDto travelDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        System.out.println("loginId = " + loginId);
        // TODO :: 만약 이거 없으면 뭐나옴? ->예외처리

        Date time = new Date();
        String localTime = format.format(time);
        List<Pin> pinList = travelDto.getPinList();

        Travel build = Travel.builder()
                .travelId(travelDto.getTravelId())
                .user(userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new))//이거 고쳐야함
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
                .transportation(travelDto.getTransportation())
                .build();

//:TODO :: Pin과 Travel연결
        for (Pin pin : pinList) {
            //TravelImages가 있음 핀마다 저장,
            locationRepository.save(pin.getLocation()); //location이 안들어올때 에러처리 해야함 TODO
//            List<TravelImage> travelImages = pin.getTravelImages();
//            System.out.println("travelImages = " + travelImages);
//            for (TravelImage travelImage : travelImages) {
//                //이미지 객체별로 파일화시켜서 DB에 저장하고, 컴퓨터에도 저장하기.
//                System.out.println("travelImage.getStored_file_path() = " + travelImage.getStored_file_path());
//                //TODO
////                 RegistTravelImage(loginId,travelImage,travelDto, pin);
//            }/
            pin.setTravel(build);
            pinRepository.save(pin);
        }

        travelRepository.save(build);


    }


    @Transactional
    public Travel travel_info(Long travelId) {
        Travel travel = travelRepository.findByTravelId(travelId).orElseThrow(CTravelNotFoundException::new);

        Hibernate.initialize(travel.getPinList());
        //lazy
        return travel;
    }

    //게시글 수정
    @Transactional
    public boolean updateTravel(UpdateTravelDto updateTravelDto){
        Travel travel = travelRepository.findByTravelId(updateTravelDto.getTravelId()).orElseThrow(CTravelNotFoundException::new);
        travel.updateTravel(updateTravelDto);
        return true;
    }


    //TODO:: Page<TraveDto>로 변환
    public Page<Travel> TravelsByLikeCount(int Page,int Size){
        PageRequest pageRequest = PageRequest.of(Page, Size, Sort.by(Sort.Direction.DESC, "likedCount"));
        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
        return memberPages;
    }

    public Page<Travel> TravelsByUpdateTime(int Page,int Size){
        PageRequest pageRequest = PageRequest.of(Page, Size, Sort.by(Sort.Direction.DESC, "append_date"));
        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
        return memberPages;
    }

//    public Page<GetTravelDto> TravelsByUsers(int Page,int Size){
//
//    }
    //TODO:: 추가적으로 가격순 , 도보/대중교통 그런 거 조회


    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

    //==비즈니스 로직==//

}
