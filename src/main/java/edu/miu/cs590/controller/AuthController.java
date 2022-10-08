package edu.miu.cs590.controller;


import edu.miu.cs590.config.jwt.JWTUtil;
import edu.miu.cs590.dto.LoginDto;
import edu.miu.cs590.exception.InvalidCredentialException;
import edu.miu.cs590.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;




    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(loginDto.getEmail());

            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new InvalidCredentialException("Invalid Login Credentials");
        }
    }
}
