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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        System.out.println("loginId = " + loginId);
        // TODO :: 만약 이거 없으면 뭐나옴? ->예외처리

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
                .transportation(travelDto.getTransportation())
                .build();

        for (Pin pin : pinList) {
//            List<TravelImage> travelImages= pin.getTravelImageList();
//            try {
//                travelImageService.createPin(pin , travelImages);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            locationRepository.save(pin.getLocation()); //location이 안들어올때 에러처리 해야함 TODO
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


    //TODO:: Page<TraveDto>로 변환 pageImpl<>(DTO, pageable, 총정보)로 하면 되는듯??
//    public Page<GetTravelDto> TravelsByLikeCount(int Page,int Size){
//        PageRequest pageRequest = PageRequest.of(Page, Size, Sort.by(Sort.Direction.DESC, "likedCount"));
//        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
//        new PageImpl<>(travelMapper.toDto())
//        return memberPages;
//
//        return new PageImpl<>(bankIntegratedManagerMapper.toDto(managers.getContent()), pageable, managers.getTotalElements());
//
//    }

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
