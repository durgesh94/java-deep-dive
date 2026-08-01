package com.learning.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Order(2)
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Authentication Filter START");

        // Simulate authentication
        String token = request.getHeader("Authorization");

        if (token == null) {
            log.warn("Authorization header is missing");
        } else {
            log.info("Authorization header found");
        }

        filterChain.doFilter(request, response);

        log.info("Authentication Filter END");
    }
}