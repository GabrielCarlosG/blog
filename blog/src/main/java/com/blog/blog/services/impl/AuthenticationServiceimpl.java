package com.blog.blog.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.blog.blog.models.User;
import com.blog.blog.repositories.UserRepository;
import com.blog.blog.request.AuthRequest;
import com.blog.blog.services.AuthenticationService;

@Service
public class AuthenticationServiceimpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        return userRepository.findByUsername(login);
    }

     @Override
    public String getToken(AuthRequest auth){
        User user = userRepository.findByUsername(auth.getUsername());
        return genereteToken(user);
    }

    public String genereteToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                .withIssuer("blog")
                .withSubject(user.getUsername())
                .withExpiresAt(getEpirationDate())
                .sign(algorithm);
        } catch(JWTCreationException exception){
            throw new RuntimeException("Fail to Generete Token" +exception.getMessage());
        }
    }

    public String validateJwtToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.require(algorithm)
            .withIssuer("blog")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException exception){
        return "";
        }
    }

    private Instant getEpirationDate(){
        return LocalDateTime.now()
        .plusHours(8)
        .toInstant(ZoneOffset.of("-03:00"));
    }
}
