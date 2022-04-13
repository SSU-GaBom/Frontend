package GaBom.Bom.repository;

import GaBom.Bom.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByBoardIdx(Integer boardIdx);
}
