package GaBom.Bom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
public class TravelImage {

    @Id
    @GeneratedValue
    @Column(name = "travel_image_id")
    private Long id;

    private String fileName;
//    private String filePathAndName;
    private String travelFileName;
    //base64 Image의 길이는 약 3천 이상임. <-- update에서는 동작하지 않는다고함.
    @Column(columnDefinition = "LONGTEXT")
    private String base64Image;

    private String identifier;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pin_id")
    @JsonBackReference
    private Pin pin;
    public TravelImage() {
    }
}



//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class TravelImage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long travel_image_Id;
//    @NotEmpty
//    private String original_file_name;
//    @NotEmpty
//    private String stored_file_path;
//
//    private long file_size;
//
//
//    //게시글 올릴 때 사용하는 속성
//    @ManyToOne
//    @JoinColumn(name = "pin_id")
//    private Pin pin;
//
//    public void updateTravelImage(String original_file_name, String stored_file_path, long file_size) {
//        this.original_file_name = original_file_name;
//        this.stored_file_path = stored_file_path;
//        this.file_size = file_size;
////        this.pin = pin;
//    }
//
//    //
////    public void updateTravelImage(String travel_original_file_name, String travel_stored_file_path, long travel_file_size) {
////        this.travel_original_file_name = travel_original_file_name;
////        this.travel_stored_file_path = travel_stored_file_path;
////        this.travel_file_size = travel_file_size;
////    }
//
//}
