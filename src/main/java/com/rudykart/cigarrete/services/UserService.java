package com.rudykart.cigarrete.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rudykart.cigarrete.dto.RegisterDto;
import com.rudykart.cigarrete.entities.Role;
import com.rudykart.cigarrete.entities.User;
import com.rudykart.cigarrete.exceptions.EmailAlreadyExistsException;
import com.rudykart.cigarrete.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user otfound", email)));
    }

    public User register(RegisterDto registerDto) {

        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        User user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
                .role(Role.valueOf(registerDto.getRole()))
                .build();
        userRepository.save(user);
        return user;
    }
}
