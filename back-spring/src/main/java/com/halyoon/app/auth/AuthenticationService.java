package com.halyoon.app.auth;

import com.halyoon.app.config.JwtService;
import com.halyoon.app.tfa.TowFactorAuthenticationService;
import com.halyoon.app.token.Token;
import com.halyoon.app.token.TokenRepository;
import com.halyoon.app.token.TokenType;
import com.halyoon.app.user.Role;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRespository repository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TowFactorAuthenticationService tfaService;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        //Gards
        if (request.getPassword() == null) throw new Exception("Password is required");
        else {
            if (repository.existsByEmail(request.getUsername())) {
                throw new IllegalStateException("User exists (:>");
            }

            var user = User.builder().firstname(request.getFullname())
                    .lastname(request.getLastname())
                    .imgUrl(request.getImgUrl())
                    .email(request.getUsername())
                    .createdAt(new Date())
                    .about(request.getAbout())
                    .location(request.getLocation())
                    .isSuperhost(request.isSuperhost())
                    .responseTime(request.getResponseTime())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .mfaEnabled(request.isMfaEnabled())
                    .build();

            if (request.isMfaEnabled())
                user.setSecret(tfaService.generateNewSecret());
            var savedUser = repository.save(user);

            var jwtToken = jwtService.generateToken(savedUser);
            saveUserToken(savedUser, jwtToken);

            return AuthenticationResponse.builder().token(jwtToken)
                    //.secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))                    //.mfaEnabled(user.isMfaEnabled())
                    .build();
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()
                    //check if user entered and if not it will throw an exception
            ));
            var user = repository.findByEmail(request.getUsername()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .error(null)
                    .build();


    }

    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {

        User user = repository.findByEmail(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException
                        (String.format("No user found  with %s", verificationRequest.getEmail())));
        if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {
            throw new BadCredentialsException("Code is not correct");

        }
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
//                .mfaEnabled(user.isMfaEnabled())
                .build();

    }
}
