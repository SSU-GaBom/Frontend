package GaBom.Bom.repository;

import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    public Optional<Image> findByUser(User user);
    public void deleteByUser(User user);

}
