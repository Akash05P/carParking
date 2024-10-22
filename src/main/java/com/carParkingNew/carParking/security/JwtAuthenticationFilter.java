package com.carParkingNew.carParking.security;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Authorization Header
            String requestHeader = request.getHeader("Authorization");
            logger.info("Header: {}", requestHeader);

            String username = null;
            String token = null;

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                token = requestHeader.substring(7); // Extract token

                try {
                    username = jwtHelper.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    logger.error("Illegal Argument while fetching the username !!", e);
                } catch (ExpiredJwtException e) {
                    logger.error("Given JWT token is expired !!", e);
                    // You can send a specific error message if token expired
                    if (!response.isCommitted()) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token expired");
                        return; // Exit without further processing
                    }
                } catch (MalformedJwtException e) {
                    logger.error("Malformed JWT Token !! Invalid Token", e);
                    if (!response.isCommitted()) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                        return; // Exit without further processing
                    }
                } catch (Exception e) {
                    logger.error("An error occurred while processing the JWT token", e);
                    if (!response.isCommitted()) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token processing error");
                        return; // Exit without further processing
                    }
                }
            } else {
                logger.warn("Invalid Header Value or Authorization header is missing!");
            }

            // Set authentication if the username is valid and authentication is not yet set
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Boolean validateToken = jwtHelper.validateToken(token, userDetails);

                if (validateToken) {
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.warn("Token validation failed!");
                }
            }

            // Continue with the filter chain
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("An exception occurred during filtering", e);
            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred");
            }
        }
    }
}
