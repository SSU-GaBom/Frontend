package GaBom.Bom.service;



import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserId(username).orElseThrow(CUserNotFoundException::new);
    }
}