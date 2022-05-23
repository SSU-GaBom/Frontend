package GaBom.Bom.repository;


import GaBom.Bom.entity.Travel;
import GaBom.Bom.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    public Optional<Travel> findByTravelId(Long travelId);
    @Transactional
    void deleteByTravelId(Long travelId);

    Page<Travel> findAll(Pageable pageable);


    Page<Travel> findByTitleContaining(String keyword,Pageable pageable);

//    Page<Travel> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
