package GaBom.Bom.repository;

import GaBom.Bom.entity.ProfileImage;
import GaBom.Bom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {
    public Optional<ProfileImage> findByUser(User user);
    public void deleteByUser(User user);

}
