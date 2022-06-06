package GaBom.Bom.service;

import GaBom.Bom.dto.LocationDto;
import GaBom.Bom.dto.TravelDto;
import GaBom.Bom.entity.Location;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationService {
    private final LocationRepository locationRepository;
    //Location 등록  :  Location이 이미 있는지 확인 -> 있으면 등록 안해도 됨.

    @Transactional
    public void RegistLoc(LocationDto locationDto){
        Long Id = locationDto.getId();
        if(locationRepository.existsById(Id)){
            return;
        }//있으니까 그냥 return
        Location location=new Location();
        locationRepository.save(Location.builder()
                .locationId(locationDto.getId())
                .address_name(locationDto.getAddress_name())
                .road_address_name(locationDto.getRoad_address_name())
                .place_name(locationDto.getPlace_name())
                .x(locationDto.getX())
                .y(locationDto.getY())
                .build());
//         .city(null) //바꿀예정
//                .state(null)

    }
    //Location 삭제 : 필요한가?


}
