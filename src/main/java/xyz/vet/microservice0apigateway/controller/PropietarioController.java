package xyz.vet.microservice0apigateway.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.PropietarioDto;
import xyz.vet.microservice0apigateway.dto.Mensaje;
import xyz.vet.microservice0apigateway.model.User;
import xyz.vet.microservice0apigateway.request.PropietarioMascotaServiceRequest;
import xyz.vet.microservice0apigateway.service.UserService;
import xyz.vet.microservice0apigateway.utils.ValidarIdentificacion;

import java.util.Objects;

@RestController
@RequestMapping("/gateway/propietario")
@CrossOrigin(origins = "**")
public class PropietarioController {
    @Autowired
    private PropietarioMascotaServiceRequest propietarioMascotaServiceRequest;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllPropietarios(){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getAllPropietarios());
    }

    @GetMapping("{propietarioId}")
    public ResponseEntity<?> getPropietarioById(@PathVariable("propietarioId") Long id){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getPropietarioById(id));
    }

    @GetMapping("detailByIdUser/{idUser}")
    public ResponseEntity<?> getPropietarioByIdUser(@PathVariable("idUser") Long id){
        return ResponseEntity.ok(propietarioMascotaServiceRequest.getPropietarioByIdUser(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PropietarioDto propietarioDto, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        if(userService.findByUsername(propietarioDto.getCedula()).isPresent())
            return new ResponseEntity<>(new Mensaje("Ya existe un propietario con el número de cédula: "+propietarioDto.getCedula()), HttpStatus.BAD_REQUEST);

        if(!ValidarIdentificacion.getInstance().validarCedula(propietarioDto.getCedula()))
            return new ResponseEntity<>(new Mensaje("Número de cédula no válida: "+propietarioDto.getCedula()), HttpStatus.BAD_REQUEST);

        if(userService.findByEmail(propietarioDto.getEmail()).isPresent())
            return new ResponseEntity<>(new Mensaje("Email en uso."), HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setUsername(propietarioDto.getCedula());
        user.setEmail(propietarioDto.getEmail());
        user.setNombre(propietarioDto.getNombre());
        user.setApellido(propietarioDto.getApellido());
        user.setPassword(propietarioDto.getCedula());

        propietarioDto.setIdUser(userService.saveUser(user).getId());

        return new ResponseEntity<>(propietarioMascotaServiceRequest.savePropietario(propietarioDto), HttpStatus.CREATED);
    }

    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody PropietarioDto propietarioDto, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        if(userService.findByUsername(propietarioDto.getCedula()).isPresent())
            return new ResponseEntity<>(new Mensaje("Ya existe un propietario con el número de cédula: "+propietarioDto.getCedula()), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(propietarioMascotaServiceRequest.savePropietario(propietarioDto), HttpStatus.CREATED);
    }

    @PutMapping("{propietarioId}")
    public ResponseEntity<?> update(@PathVariable("propietarioId") Long propietarioId, @RequestBody Object propietario){
        return new ResponseEntity<>(propietarioMascotaServiceRequest.updatePropietario(propietarioId, propietario), HttpStatus.OK);
    }

    @DeleteMapping("{propietarioId}")
    public ResponseEntity<?> delete(@PathVariable("propietarioId") Long propietarioId){
        propietarioMascotaServiceRequest.deletePropietario(propietarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
