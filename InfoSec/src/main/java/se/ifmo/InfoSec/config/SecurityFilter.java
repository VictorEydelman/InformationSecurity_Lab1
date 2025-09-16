package se.ifmo.InfoSec.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.ifmo.InfoSec.service.JWTService;

import java.io.IOException;

public class SecurityFilter implements Filter {
    private final JWTService jwtService = new JWTService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String requestURI = request.getRequestURI();
        System.out.print(requestURI);
        if (requestURI.startsWith("/api/user/")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                String jwt = token.substring(7);
                String username = jwtService.extractUsername(jwt);
                if (username != null && jwtService.isTokenValid(jwt,username)) {
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }
            }
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
