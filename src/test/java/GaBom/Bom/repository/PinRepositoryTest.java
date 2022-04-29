package GaBom.Bom.repository;

import GaBom.Bom.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PinRepositoryTest {
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PinRepository pinRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    CardRepository cardRepository;

    @Test
    public void ConnectPinAndCard () throws Exception {
        //given
        Location location = new Location();
        location.setLatitude(3.555f);
        location.setLongitude(2.333f);
        locationRepository.save(location);

//        Location location2 = new Location();
//        location2.setLatitude(12.55f);
//        location2.setLongitude(51.23f);
//        locationRepository.save(location2);

        Pin pin = new Pin();
        pin.setLocation(location); // 이걸 set말고 다르게?

//        Pin pin2 = new Pin();
//        pin2.setLocation(location2); // 이걸 set말고 다르게?

        Card card = new Card("마포여행꿀잼","path1");
        Card card2 = new Card("마포여행꿀잼2","path21");
//        Card card3 = new Card("부산","path212");

        pin.add(card);
        pin.add(card2);
//        pin2.add(card3);

        //카드저장
        cardRepository.save(card);
        cardRepository.save(card2);

        List<Card> all = cardRepository.findAll();
        for (Card card1 : all) {
            System.out.println("card 의 내용 = " + card1.getContent());
        }

        pinRepository.save(pin);
//        cardRepository.save(card3);
        //when
        
        //then
        
    }
    
    //각자 잘 연결 되는지 확인.
    @Test
    public void PinRepositoryTest() {
        User createuser = new User();
        createuser.setUserName("시온");
        userRepository.save(createuser);

        Travel travel2 = new Travel("Travel_title", true, 0, "goodstate", "goodcity");
        Travel travel3 = new Travel("Travel2_title", true, 1, "goo2dstate", "goo3city");

        // given
        Location location = new Location();
        location.setLatitude(3.555f);
        location.setLongitude(2.333f);
        locationRepository.save(location);

        Location location2 = new Location();
        location2.setLatitude(12.55f);
        location2.setLongitude(51.23f);
        locationRepository.save(location2);

        Pin pin = new Pin();
        pin.setLocation(location); // 이걸 set말고 다르게?
//        pinRepository.save(pin);
        Pin pin2 = new Pin();
        pin2.setLocation(location2); // 이걸 set말고 다르게?
//        pinRepository.save(pin2);

        Card card = new Card("마포여행꿀잼","path1");
        Card card2 = new Card("마포여행꿀잼2","path21");
        Card card3 = new Card("부산","path212");

        pin.add(card);
        pin.add(card2);
        pin2.add(card3);

        //카드저장
        cardRepository.save(card);
        cardRepository.save(card2);
        cardRepository.save(card3);
        // when

        //핀 저장
        pinRepository.save(pin);
        pinRepository.save(pin2);

        travel2.add(pin);
        travel2.add(pin2);


        createuser.add(travel2);
        createuser.add(travel3); //이게 작동이 안되는것같음.

        travelRepository.save(travel2);
        travelRepository.save(travel3);
        // then
//        Assertions.assertThat(travel2.getPinList().size()).isEqualTo(2);
//        Assertions.assertThat(travel2.getPinList().get(0).getLocation()
//                .getAddress()).isEqualTo(location.getAddress());
//        Assertions.assertThat(travel2.getPinList().get(0).getLocation()
//                .getLatitude()).isEqualTo(location.getLatitude());
//        Assertions.assertThat(travel2.getPinList().get(1).getLocation()
//                .getLatitude()).isEqualTo(location2.getLatitude());
    }
}