package com.budgetbuddy.budgetbuddyapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.HashMap;
import java.util.Map;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AppUserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        System.out.println(path);
        if(path.contains(".html") || path.contains(".css") || path.contains(".js")) return true;
        return path.startsWith("/login") ||
               path.startsWith("/signup") ||
               path.startsWith("/dashboard") ||
               path.startsWith("/pexp")
               ;
    }

    @Override
    protected void doFilterInternal(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response,
            jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, java.io.IOException {

        System.out.println("Checking for jwt token...");

        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            Long userId = null;

            if (authHeader == null){
                throw new JwtException("Auth Header not present");
            }

            // 1. Extract JWT token from header
            token = authHeader.substring(7);
            userId = jwtService.extractUserId(token);


            // 2. If we got username and SecurityContext is empty, authenticate user
            if (userId == null) {
                throw new JwtException("Invalid userId");
            }
            if(SecurityContextHolder.getContext().getAuthentication() != null) {
                throw new JwtException("Invalid Auth context");
            }

            UserDetails userDetails = userDetailsService.loadUserByUserId(userId);

            if (!jwtService.validateToken(token, (AppUserDetails) userDetails)) {
                throw new JwtException("Invalid or expired token");
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 🔹 Set authentication in Spring Security context
            SecurityContextHolder.getContext().setAuthentication(authToken);

            System.out.println("User authenticated");
        }
        catch(Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            System.out.println(e.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(error));
            return;
        }



        filterChain.doFilter(request, response);
    }
}
