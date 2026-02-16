package com.subrat.bbank.service.impl;

import com.subrat.bbank.dto.AuthRequest;
import com.subrat.bbank.dto.AuthResponse;
import com.subrat.bbank.dto.RegisterRequest;
import com.subrat.bbank.entity.User;
import com.subrat.bbank.enums.Role;
import com.subrat.bbank.exception.BadRequestException;
import com.subrat.bbank.repository.UserRepository;
import com.subrat.bbank.service.AuthService;
import com.subrat.bbank.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        Role role = request.getRole() == null ? Role.CUSTOMER : request.getRole();
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        return AuthResponse.builder()
                .token(jwtUtil.generateToken(userDetails))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        return AuthResponse.builder()
                .token(jwtUtil.generateToken(userDetails))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
