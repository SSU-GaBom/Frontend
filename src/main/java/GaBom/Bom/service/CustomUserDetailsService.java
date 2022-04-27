package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserId(username).orElseThrow(CUserNotFoundException::new);
    }
}
