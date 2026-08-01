package com.learning.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        logger.info("========== Logging Filter START ==========");
        logger.info("Incoming Request : {} {}",
                request.getMethod(),
                request.getRequestURI());

        long start = System.currentTimeMillis();

        filterChain.doFilter(request, response);

        long end = System.currentTimeMillis();

        logger.info("API {} executed in {} ms",
                request.getRequestURI(),
                end - start);

        logger.info("Outgoing Response : {}",
                response.getStatus());
        logger.info("========== Logging Filter END ==========");
    }
}