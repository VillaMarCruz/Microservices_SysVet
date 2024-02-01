package xyz.vet.microservice0apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.vet.microservice0apigateway.model.User;
import xyz.vet.microservice0apigateway.repository.UserRepository;
import xyz.vet.microservice0apigateway.security.UserPrincipal;
import xyz.vet.microservice0apigateway.security.jwt.JwtProvider;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;

    /*
    * Login del Usuario
    * */
    @Override
    public User signInAndReturnJWT(User signInRequest){

        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("El usuario no fue encontrado" + signInRequest.getEmail()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(userPrincipal);

        User signInUser = userPrincipal.getUser();

        signInUser.setToken(jwt);

        return signInUser;

    }

}
