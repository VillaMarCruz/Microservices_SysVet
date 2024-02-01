package xyz.vet.microservice0apigateway.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import xyz.vet.microservice0apigateway.model.User;
import xyz.vet.microservice0apigateway.security.UserPrincipal;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    String generateToken(User user);

    Authentication getAuthentincation(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
