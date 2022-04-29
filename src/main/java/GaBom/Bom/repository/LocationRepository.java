package GaBom.Bom.repository;


import GaBom.Bom.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    public boolean existsByLocationId(Long LocationId);
}
