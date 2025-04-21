package com.blog.blog.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.blog.request.AuthRequest;

public interface AuthenticationService {
    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;

    String getToken(AuthRequest auth);

}
