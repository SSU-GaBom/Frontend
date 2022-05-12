package GaBom.Bom.dto;

import lombok.Getter;

@Getter
public class LocationDto {
    private Long Id;
    private String address_name;
    private String road_address_name;
    private String place_name;
    private Float x;//x
    private Float y;//y

    public LocationDto(Long Id, String address_name, String road_address_name, String place_name, Float x, Float y) {
        this.Id = Id;
        this.address_name = address_name;
        this.road_address_name = road_address_name;
        this.place_name = place_name;
        this.x = x;
        this.y = y;
    }
}
