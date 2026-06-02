package edu.marcio.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.login.LoginRequest;
import edu.marcio.stock.dto.login.LoginResponse;
import edu.marcio.stock.service.CustomUserDetailsService;
import edu.marcio.stock.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> postMethodName(@Valid @RequestBody LoginRequest body) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
        // if user does not exist an erro will be throwned
        customUserDetailsService.loadUserByUsername(body.getEmail());
        // if noe error occur, the token will be created
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(jwtUtil.generateToken(body.getEmail())));
    }

}
