package com.example.gateways.service;

import com.example.gateways.service.dto.user.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.gateways.entity.User;
import com.example.gateways.repository.AuthorityRepository;
import com.example.gateways.repository.UserRepository;
import com.example.gateways.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public long saveUser( UserDTO request ) {
        final var user = new User( request.getUsername() );
        user.setPassword( passwordEncoder.encode( request.getPassword() ) );
        final var roles =  this.authorityRepository.findAllById( request.getAuthorities() );
        user.setAuthorities(roles);
        final var result = this.userRepository.save( user );
        return result.getId();
    }
}
