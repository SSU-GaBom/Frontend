package GaBom.Bom.repository;

import GaBom.Bom.dto.LocationDto;
import GaBom.Bom.entity.Location;
import GaBom.Bom.service.LocationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;
    @Test
    @DisplayName("exists 함수 되는지 확인")
    public void  func() throws Exception {
        //given
        Location location = new Location();
        location.setLocationId(2L);
        location.setLatitude(3.22f);
        location.setLongitude(3.222f);
        locationRepository.save(location);
        //when
        boolean b = locationRepository.existsByLocationId(location.getLocationId());
        boolean c = locationRepository.existsByLocationId(3L);
        //then
        Assertions.assertThat(b).isEqualTo(true);
        Assertions.assertThat(c).isEqualTo(false);

    }

    @Test
    @DisplayName("Location 저장 되는지 확인")
    public void  func1() throws Exception {
        LocationDto locationDto = new LocationDto(1231241L,"지번주소","도로명주소","가게이름",3.123213f,22.1231231f);
        //given
        //when
        //then

    }

}