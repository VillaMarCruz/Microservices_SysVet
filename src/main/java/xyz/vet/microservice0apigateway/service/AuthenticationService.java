package xyz.vet.microservice0apigateway.service;

import xyz.vet.microservice0apigateway.model.User;

public interface AuthenticationService {

    /*
    * Login del Usuario
    * */
    User signInAndReturnJWT(User signInRequest);
}
