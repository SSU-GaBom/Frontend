//package GaBom.Bom.service;
//
//import GaBom.Bom.advice.exception.CUserNotFoundException;
//import GaBom.Bom.entity.Location;
//import GaBom.Bom.entity.Pin;
//import GaBom.Bom.entity.Travel;
//import GaBom.Bom.entity.User;
//import GaBom.Bom.repository.LocationRepository;
//import GaBom.Bom.repository.PinRepository;
//import GaBom.Bom.repository.TravelRepository;
//import GaBom.Bom.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class ZzimServiceTest {
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    TravelRepository travelRepository;
//    @Autowired
//    TravelService travelService;
//    @Autowired
//    LocationRepository locationRepository;
//    @Autowired
//    PinRepository pinRepository;
//
//
//    @Test
//    public void func () throws Exception {
//        User createuser = new User();
//        createuser.setUserName("시온");
//        userRepository.save(createuser);
//
//        Location location = new Location().builder()
//                .address_name("dd")
//                .x(3.22f)
//                .y(4.22f)
//                .build();
//
//        locationRepository.save(location);
//        Pin pin1 = new Pin();
//        pin1.setPinId(1L);
//        pin1.setLocationContent("핀내용1");
//        pin1.setLocation(location);
//        //
//        Pin pin2 = new Pin();
//        pin2.setPinId(2L);
//        pin2.setLocationContent("핀내용2");
//        pin2.setLocation(location);
//
//
//        pinRepository.save(pin1);
//        pinRepository.save(pin1);
//
//        List<Pin> list = new ArrayList<>();
//        list.add(pin1);
//        list.add(pin2);
//
//        Travel travel = Travel.builder()
//                .myuser(createuser)//이거 고쳐야함
//                .pinList(list)
//                .title("제목1")
//                .city("도시")
//                .state("경기도")
//                .appendDate("2022-05-18 19:02:015")
//                .updateDate("2022-05-18 19:02:015")
//                .startDate("2022-05-18")
//                .endDate("2022-05-18")
//                .expense(400)
//                .content("경기도")
//                .isShared(false)
//                .likedCount(0)
//                .ZzimCount(0)
//                .transportation("대중교통")
//                .build();
//
//        travelRepository.save(travel);
//
//        Travel travel1 = travelRepository.findAll().get(0);
//        System.out.println("travel1.getTitle() = " + travel1.getTitle());
//        System.out.println("travel1.getLocationContent(). = " + travel1.getPinList().get(1).getLocationContent());
//
//    }
//}