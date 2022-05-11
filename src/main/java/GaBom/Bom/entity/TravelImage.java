package GaBom.Bom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travel_image_Id;
    @NotEmpty
    private String original_file_name;
    @NotEmpty
    private String stored_file_path;

    private long file_size;


    //게시글 올릴 때 사용하는 속성
    @ManyToOne
    @JoinColumn(name = "pin_id")
    private Pin pin;

    public void updateTravelImage(String original_file_name, String stored_file_path, long file_size) {
        this.original_file_name = original_file_name;
        this.stored_file_path = stored_file_path;
        this.file_size = file_size;
//        this.pin = pin;
    }

    //
//    public void updateTravelImage(String travel_original_file_name, String travel_stored_file_path, long travel_file_size) {
//        this.travel_original_file_name = travel_original_file_name;
//        this.travel_stored_file_path = travel_stored_file_path;
//        this.travel_file_size = travel_file_size;
//    }

}