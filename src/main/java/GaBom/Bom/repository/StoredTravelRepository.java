package GaBom.Bom.repository;


import GaBom.Bom.entity.StoredTravel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredTravelRepository extends JpaRepository<StoredTravel, Long> {
}
