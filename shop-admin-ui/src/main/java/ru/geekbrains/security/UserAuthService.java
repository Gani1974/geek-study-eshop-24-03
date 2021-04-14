package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.repo.UserRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList()))
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
