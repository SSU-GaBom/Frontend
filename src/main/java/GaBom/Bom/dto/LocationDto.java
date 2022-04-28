package GaBom.Bom.dto;

import lombok.Getter;

@Getter
public class LocationDto {
    private Long locationId;
    private String address_name;
    private String road_address_name;
    private String place_name;
    private Float latitude;//x
    private Float longitude;//y

    public LocationDto(Long locationId, String address_name, String road_address_name, String place_name, Float latitude, Float longitude) {
        this.locationId = locationId;
        this.address_name = address_name;
        this.road_address_name = road_address_name;
        this.place_name = place_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
