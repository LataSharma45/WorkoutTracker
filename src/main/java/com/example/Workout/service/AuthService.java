package com.example.Workout.service;

import com.example.Workout.Repository.UserRepository;
import com.example.Workout.dto.AuthDTO;
import com.example.Workout.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String register(AuthDTO request) {
    if(repository.findByUsername(request.getUsername()).isPresent()){
        throw  new IllegalArgumentException("Username already exsits");
    }
    var user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    repository.save(user);
    return jwtService.generateToken(user);
    }
    public  String login(AuthDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken =jwtService.generateToken(user);
        return jwtToken;
    }
    }

