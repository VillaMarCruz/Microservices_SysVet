package xyz.vet.microservice0apigateway.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.Mensaje;
import xyz.vet.microservice0apigateway.dto.UserDto;
import xyz.vet.microservice0apigateway.model.Role;
import xyz.vet.microservice0apigateway.model.User;
import xyz.vet.microservice0apigateway.security.UserPrincipal;
import xyz.vet.microservice0apigateway.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "**")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserAll(){
        List<User> usuarios = userService.listUser();
        return new ResponseEntity<List<User>>(usuarios, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> signUp (@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        if(userService.findByUsername(userDto.getUsername()).isPresent())
            return new ResponseEntity<>(new Mensaje("Ya existe nombre de usuario."), HttpStatus.BAD_REQUEST);
        if(userService.findByEmail(userDto.getEmail()).isPresent())
            return new ResponseEntity<>(new Mensaje("Email en uso."), HttpStatus.BAD_REQUEST);

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setPassword(userDto.getPassword());

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody UserPrincipal user, @PathVariable Role role){
        String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        if(!authorities.equals("ROLE_USER")){
            userService.changeRole(role, user.getUsername());
            return new ResponseEntity<>("Rol cambiado", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("El usuario no tiene los roles para cambiar usuario", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Usuario eliminado!", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return new ResponseEntity<>(userService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }

}
