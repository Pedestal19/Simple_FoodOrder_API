package com.foodOrder.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET = "121341werw244234w25234wewerwerwer";
    public static final long EXPIRATION_TIME = 86400000; // a day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            User loginUser =
                    new ObjectMapper()
                            .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {
        String token =
                JWT.create()
                        .withSubject(
                                ((User) authResult.getPrincipal()).getUsername()) // Payload register sub claim
                        .withExpiresAt(
                                new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // JWT token validity time
                        .sign(Algorithm.HMAC512(SECRET.getBytes())); // JWT Signature
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
