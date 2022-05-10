package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.LocationDto;
import GaBom.Bom.dto.SignUpUserDto;
import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.dto.UpdateTravelDto;
import GaBom.Bom.entity.*;
import GaBom.Bom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CardRepository cardRepository;
    private final PinRepository pinRepository;

//    @Transactional
//    public boolean save(String username, TravelDto travelDto){
//        User user = userRepository.findByUserName(username);
//        //만약 userName이 없으면? -> 에러처리. 근데 로그인세션으로 할거니까 그때 보고?하기.
//        user.add(travelDto);
//        travelRepository.save(travel);
//        return true;
//    }
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Transactional
    public void joinTravel (TravelDto travelDto) {
        System.out.println("joinStart");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        //유저아이디

        System.out.println("ID :: loginId = " + loginId);

        List<Pin> pinList = travelDto.getPinList();
        for (Pin pin : pinList) {
            //pin에 있는 location 저장 후 dto 말고 객체로 저장하기.
            //이유 : 이미 날라온 travelDto에는 location으로 있기때문.( 고쳐볼까 )
            locationRepository.save(pin.getLocation());
            //pin에 있는 card 저장 후
            for (Card card : pin.getCardList()) {
                card.setPin(pin);
//                pin.add(card);
                cardRepository.save(card);
            }
            pinRepository.save(pin);
        }
        travelRepository.save(Travel.builder()
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
                    .transportation(null)
                    .build());
    }


    public Travel travel_info(Long travelId) {
        return travelRepository.findByTravelId(travelId);
    }

    //게시글 수정
    @Transactional
    public boolean updateTravel(UpdateTravelDto updateTravelDto){
        Travel travel = travelRepository.findByTravelId(updateTravelDto.getTravelId());
        if(travel==null){ //
            return false;
        }
        travel.updateTravel(updateTravelDto);
        return true;
    }


    public Page<Travel> TravelsByLikeCount(int Page,int Size){
        PageRequest pageRequest = PageRequest.of(Page, Size, Sort.by(Sort.Direction.DESC, "likedCount"));
        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
        return memberPages;
    }

//    Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("likeCount").descending());



    @Transactional
    public void deleteTravel(Long travelId) { travelRepository.deleteByTravelId(travelId);
    }

    //==비즈니스 로직==//

}
