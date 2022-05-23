package GaBom.Bom.repository;


import GaBom.Bom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserId(String userId);
    public boolean existsByUserNameAndEmail(String userName, String email);
    public boolean existsByUserIdAndEmail(String userId, String email);
    public boolean existsByNickName(String NickName);
    public boolean existsByRefreshToken(String refreshToken);

    public Optional<User> findByUserId(String userId);
    public Optional<User> findByNickName(String nickName);
    public Optional<User> findByUserNameAndEmail(String userName, String email);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUserName(String username);

}
