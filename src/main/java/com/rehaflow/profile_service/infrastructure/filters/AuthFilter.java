package com.rehaflow.profile_service.infrastructure.filters;

import com.rehaflow.sharedlib.filters.auth.AuthProcessor;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import com.rehaflow.sharedlib.services.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    @Value("${jwt.secret.session}")
    private String accessTokenSecret;

    private final AuthProcessor authProcessor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] authHeaderPats = request.getHeader("auth").split(" ");

        TokenHeaderDTO tokenHeaderDTO = this.authProcessor.validateToken(authHeaderPats[1], accessTokenSecret);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(tokenHeaderDTO, null, List.of());

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}
