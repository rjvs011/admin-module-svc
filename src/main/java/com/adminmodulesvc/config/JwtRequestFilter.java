package com.adminmodulesvc.config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adminmodulesvc.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String jwtToken = extractJwtToken(request);
        if (StringUtils.hasText(jwtToken) && validateJwtToken(jwtToken)) {
            String username = extractUsername(jwtToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Load user details and set the authentication in the SecurityContext
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            return bearerToken.substring(AUTHORIZATION_HEADER_PREFIX.length());
        }
        return null;
    }

    private boolean validateJwtToken(String jwtToken) {
        // Add your JWT token validation logic here
        // You can use a library like Auth0 Java JWT to validate the token
        // For example:
        // Algorithm algorithm = Algorithm.HMAC256("your_secret");
        // JWTVerifier verifier = JWT.require(algorithm).build();
        // verifier.verify(jwtToken);
        // return true if the token is valid, false otherwise
        return true;
    }

    private String extractUsername(String jwtToken) {
        // Extract the username from the JWT token
        // You can decode the token to extract the username
        // For example:
        // DecodedJWT decodedJWT = JWT.decode(jwtToken);
        // return decodedJWT.getSubject();
        // Replace the logic with your implementation
        return "admin";
    }
}

