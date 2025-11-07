package com.tss.api_gateway.filter;

import com.tss.api_gateway.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        logger.info("Authorization Header: {}", bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            logger.info("Extracted Token: {}", token.substring(0, Math.min(20, token.length())) + "...");
            return token;
        }

        return null;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            logger.info("Processing request for path: {}", path);

            // Skip authentication for auth endpoints
            if (isAuthEndpoint(request)) {
                logger.info("Skipping JWT validation for auth endpoint: {}", path);
                return chain.filter(exchange);
            }

            String token = getTokenFromRequest(request);

            // Check if token exists
            if (!StringUtils.hasText(token)) {
                logger.error("Missing or invalid authorization header");
                return onError(exchange, "Missing or invalid authorization header", HttpStatus.UNAUTHORIZED);
            }

            try {
                // Validate token
                logger.info("Validating JWT token...");
                if (!jwtTokenProvider.validateToken(token)) {
                    logger.error("Token validation returned false");
                    return onError(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
                }
                logger.info("JWT token validated successfully");
            } catch (Exception e) {
                logger.error("Token validation failed with exception: {}", e.getMessage(), e);
                return onError(exchange, "Token validation failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isAuthEndpoint(ServerHttpRequest request) {
        String path = request.getURI().getPath();
        return path.contains("/auth/");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        logger.error("Returning error: {} with status: {}", err, httpStatus);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
