package com.example.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @SuppressWarnings("unused")
	private final AuthenticationManager authenticationManager;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info("Username is {}", username);
//        log.info("Password is {}", password);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(authenticationToken);
//    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        //ma thong bao nen de cho khac va ma hoa
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(30, ChronoUnit.MINUTES);
        Instant expiration_refresh = issuedAt.plus(60, ChronoUnit.MINUTES);
        
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiration)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiration_refresh)
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
