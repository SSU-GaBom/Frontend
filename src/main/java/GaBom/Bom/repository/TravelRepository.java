package GaBom.Bom.repository;


import GaBom.Bom.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel findByTravelId(Long travelId);

    @Transactional
    void deleteByTravelId(Long travelId);

    Page<Travel> findAll(Pageable pageable);
}
