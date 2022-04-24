package GaBom.Bom.repository;


import GaBom.Bom.entity.Follow;
import GaBom.Bom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    public Optional<Follow> findFollowByFromUserAndToUser(User fromUser, User toUser);
    public Optional<Boolean> existsByFromUserAndToUser(User fromUser, User toUser);

}
