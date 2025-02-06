package com.example.springsecurityjwt.services;

import com.example.springsecurityjwt.models.UserEntity;
import com.example.springsecurityjwt.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe: " + username));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name())));

        userEntity.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                .forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getName().name())));

        return new User(userEntity.getUsername(), userEntity.getPassword(),
                userEntity.isEnabled(), userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(), authorities);
    }
}
