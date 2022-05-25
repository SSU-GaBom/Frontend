package GaBom.Bom.service;

import GaBom.Bom.dto.LocationDto;
import GaBom.Bom.entity.Location;
import GaBom.Bom.repository.LocationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationServiceTest {
//    @Autowired
//    LocationRepository locationRepository;
//    @Autowired
//    LocationService locationService;
//
//    @Test
//    @DisplayName("Location 저장 되는지 확인")
//    public void  func1() throws Exception {
//        LocationDto locationDto = new LocationDto(1231241L,"지번주소","도로명주소","가게이름",3.123213f,22.1231231f);
//        LocationDto locationDto2 = new LocationDto(12312241L,"지번주소1","도로명주소2","가게이름3",3.1232413f,22.12531231f);
//        //given
//        locationService.RegistLoc(locationDto);
//        locationService.RegistLoc(locationDto2);
//        //when
//        //then
//        List<Location> all = locationRepository.findAll();
//
//        Assertions.assertThat(all.size()).isEqualTo(2);
//        Assertions.assertThat(all.get(1).getPlace_name()).isEqualTo("가게이름3");
//
//    }
}