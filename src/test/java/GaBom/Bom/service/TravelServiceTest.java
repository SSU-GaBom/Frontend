package GaBom.Bom.service;

import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.entity.*;
import GaBom.Bom.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TravelServiceTest {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    TravelRepository travelRepository;
//    @Autowired
//    TravelService travelService;
//    @Autowired
//    LocationRepository locationRepository;
//
//    @Autowired
//    CardRepository cardRepository;
//    @Autowired
//    PinRepository   pinRepository;
//
//    @Test
//    @DisplayName("location,pin,card가 TravelDto에서 확인")
//    public void joinTravelTest() {
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//
//        Location location = new Location();
////        location.setAddress("서울시 마포구 10호");
//        location.setLatitude(3.555f);
//        location.setLocationId(1231231L);
//        location.setLongitude(2.333f);
////        locationRepository.save(location);
//
//        Location location2 = new Location();
////        location2.setAddress("부산시 마포구 10호");
//        location2.setLatitude(12.55f);
//        location2.setLocationId(123L);
//        location2.setLongitude(51.23f);
////        locationRepository.save(location2);
//
//        Pin pin = new Pin();
//        pin.setLocation(location); // 이걸 set말고 다르게?
//        Pin pin2 = new Pin();
//        pin2.setLocation(location2); // 이걸 set말고 다르게?
//
//        Card card = new Card("마포여행꿀잼","path1");
//        Card card2 = new Card("마포여행꿀잼2","path21");
//        Card card3 = new Card("부산","path212");
////        pin.add(card);
////        pin.add(card2);
////        pin2.add(card3);
//        //카드저장
////        cardRepository.save(card);
////        cardRepository.save(card2);
////        cardRepository.save(card3);
//        // when
//        //핀 저장
////        pinRepository.save(pin);
////        pinRepository.save(pin2);
//
//        List<Pin> pinList = new ArrayList<Pin>();
//        pinList.add(pin);
//        pinList.add(pin2);
////        TravelDto traveldto = new TravelDto(1L,"Travel_Title","경기도","서울시","2017-02-30","2017-02-31", pinList);
//
//        System.out.println("pinList = " + pinList);
//
////        travelService.joinTravel(traveldto);
//
////        Assertions.assertThat(travelRepository.findAll().get(0).getTitle()).isEqualTo(traveldto.getTitle());
//    }
//
//    @Test
//    @DisplayName("Location Id 따로 넣으면서하기")
//    public void fun3 () throws Exception {
//        //given
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//
//        Location location = new Location();
//        //지금 setLocationID때문에 안됨, @GeneratedValue 설정 뭘로하지?
////        location.setLocationId(123L); //이거
////        location.setAddress("서울시 마포구 10호");
//        location.setLatitude(3.555f);
//        location.setLongitude(2.333f);
//        locationRepository.save(location);
//        //when
//
//        //then
//
//    }
//
//    @Test
//    @DisplayName("location,pin,card를 바로 처리하기")
//    public void joi1nTravelTest() {
//        User createuser = new User();
//        createuser.setUserName("시온");
//        createuser.setUserId("rkskek");
//        userRepository.save(createuser);
//
//        Location location = new Location();
//        location.setLocationId(123L); //이거
////        location.setAddress("서울시 마포구 10호");
//        location.setLatitude(3.555f);
//        location.setLongitude(2.333f);
//
//        Location location2 = new Location();
//        location.setLocationId(123211223L);
////        location2.setAddress("부산시 마포구 10호");
//        location2.setLatitude(12.55f);
//        location2.setLongitude(51.23f);
//
//
//        Pin pin = new Pin();
//        pin.setLocation(location); // 이걸 set말고 다르게?
//        Pin pin2 = new Pin();
//        pin2.setLocation(location2); // 이걸 set말고 다르게?
//
//        Card card = new Card("마포여행꿀잼","path1");
//        Card card2 = new Card("마포여행꿀잼2","path21");
//        Card card3 = new Card("부산","path212");
//
//        System.out.println(" HERE!!!");
//
//        List<Pin> pinList = new ArrayList<Pin>();
//        pinList.add(pin);
//        pinList.add(pin2);
////        TravelDto traveldto = new TravelDto(1L,"Travel_Title","경기도","서울시","2017-02-30","2017-02-31", pinList);
//        TravelDto traveldto = new TravelDto(1L,"Travel_Title","경기도","서울시","2017-02-30","2017-02-31", pinList,100,"꿀잼");
//
//
////        travelService.joinTravel("시온",traveldto);
//        travelService.joinTravel(traveldto);
//
//        Travel travel1 = travelRepository.findAll().get(0);
//        System.out.println("travel1 = " + travel1);
//
//        List<User> all = userRepository.findAll();
//        for (User user : all) {
//            System.out.println("user = " + user);
//        }
//        List<Travel> all1 = travelRepository.findAll();
//        for (Travel travel : all1) {
//            System.out.println("travel = " + travel);
//        }
//        List<Pin> all2 = pinRepository.findAll();
//        for (Pin pin1 : all2) {
//            System.out.println("pin1 = " + pin1);
//        }
//        List<Card> all3 = cardRepository.findAll();
//        for (Card card1 : all3) {
//            System.out.println("card1 = " + card1);
//        }
//    }
//
//
//
//
//    @Test
//    public void OneUserAndTwo_Travel_Add() throws InterruptedException { // 한 유저에 여러 트레블 안됨.
//        //+ 그냥 두 유저에 세 트레블 연결안됨
//        // given
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
//        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
//
//        createuser.add(travel2);
//        createuser.add(travel3);
//
//        //when
//        travelRepository.save(travel2);
//        travelRepository.save(travel3);
//
//        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
//        Travel FindTravel = travelService.travel_info(travel2.getTravelId());
//
//        System.out.println("FindTravel.getState() = " + FindTravel.getState());
//        //then
//
//
////        assertThat(FindTravel).isEqualTo(travel2);
////
////        assertThat(FindTravel.getCity()).isEqualTo("goodcit2y");
//
//    }
//    @Test
//    public void PutPins () throws Exception {
//        //given
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
//        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
//
//        createuser.add(travel2);
//        createuser.add(travel3);
//
//        //when
//        travelRepository.save(travel2);
//        travelRepository.save(travel3);
//
//        System.out.println("travel2.getTravelId() = " + travel2.getTravelId());
//        Thread.sleep(10000);
//        Travel FindTravel = travelService.travel_info(travel2.getTravelId());
//        Thread.sleep(10000);
//
//        System.out.println("FindTravel.getState() = " + FindTravel.getState());
//        Thread.sleep(10000);
//        //when
//
//        //then
//
//    }
//
////    @Test
////    public void UpdateTest() throws InterruptedException {
////        // given
////        User createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
////        userRepository.save(createuser);
////        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
////        Travel travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
////        createuser.add(travel2);
////        createuser.add(travel1);
////        //when
////        travelRepository.save(travel2);
////        travelRepository.save(travel1);
////        // when
////        System.out.println("travel1.getCreatedAt() = " + travel1.getCreatedAt());
////        System.out.println("travel1.UpdateAt() = " + travel1.getUpdateAt());
////        UpdateTravelDto dto = new UpdateTravelDto();
////        dto.setTravelId(3L);
////        dto.setContent("new Content!!");
////        dto.setTitle("new Title!");
////        Thread.sleep(1000);
////        List<Travel> all = travelRepository.findAll();
////        System.out.println(" before---");
////        for (Travel travel : all) {
////            System.out.println("travel.getTitle() = " + travel.getTitle());
////            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
////        }
////        System.out.println("dto.getTravelId() = " + dto.getTravelId());
////        travelService.updateTravel(dto);
////        List<Travel> all2 = travelRepository.findAll();
////        System.out.println(" after---");
////        for (Travel travel : all2) {
////            System.out.println("travel.getTitle() = " + travel.getTitle());
////            System.out.println("travel.getUpdate() = " + travel.getUpdateAt());
////        }
////        // then
////        Travel findTravel = travelRepository.findByTravelId(travel1.getTravelId());
////        System.out.println("travel1.getTravelId() = " + travel1.getTravelId());
////        System.out.println("findTravel.getTravelId() = " + findTravel.getTravelId());
////        assertThat(findTravel.getContent()).isEqualTo("new Content!!");
////    }
//
////    private void MakeUser_Travels(User createuser,Travel travel1,Travel travel2) {
////        createuser = new User("fish","123", "eee@naver.com", "sion", "auth", "2022-02-22", "2022-02-22", "path", 0);
////        userRepository.save(createuser);
////        travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
////        travel1 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");
////        createuser.add(travel2);
////        createuser.add(travel1);
////        //when
////        travelRepository.save(travel2);
////        travelRepository.save(travel1);
////    }
//
//    @Test
//    @DisplayName("페이징 테스트(list)")
//    void list() throws InterruptedException {
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//        travelRepository.save(new Travel("Travel1_title", true, 1, "goo2dstate", "goo3city"));
//        travelRepository.save(new Travel("Travel2_title", true, 7, "goo2dstate", "goo3city"));
//        travelRepository.save(new Travel("Travel3_title", true, 100, "goo2dstate", "goo3city"));
//        travelRepository.save(new Travel("Travel4_title", true, 6, "goo2dstate", "goo3city"));
//        travelRepository.save(new Travel("Travel5_title", true, 10, "goo2dstate", "goo3city"));
//        // given
//
//        // when
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "likedCount"));
//        Page<Travel> memberPages = travelRepository.findAll(pageRequest);
//
//        for (Travel memberPage : memberPages) {
//            System.out.println("memberPage.getTitle() = " + memberPage.getTitle());
//            System.out.println("memberPage.getLikeCount() = " + memberPage.getLikedCount());
//        }
//        System.out.println("end");
//        // then
//    }
//    @Test
//    public void Put_Pins_Card () throws Exception {
//        //given
//
//        //when
//
//        //then
//
//    }
}