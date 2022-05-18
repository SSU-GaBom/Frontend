package GaBom.Bom.repository;

//import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.TravelImage;
import GaBom.Bom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelImageRepository extends JpaRepository<TravelImage, Long> {

}
