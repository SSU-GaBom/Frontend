package GaBom.Bom.repository;

import GaBom.Bom.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

    public Optional<PasswordToken> findByUserNo(Long userNo);
    public Boolean existsByUserNo(Long userNo);
}
