package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.dto.Mensaje;
import xyz.vet.microservice0apigateway.request.AgendamientoServiceRequest;
import xyz.vet.microservice0apigateway.request.EmailServiceRequest;
import xyz.vet.microservice0apigateway.request.InventarioServiceRequest;

@RestController
@RequestMapping("/gateway/agendamiento")
@CrossOrigin(origins = "**")
public class AgendamientoController {
    @Autowired
    AgendamientoServiceRequest agendamientoServiceRequest;

    @Autowired
    private EmailServiceRequest emailServiceRequest;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllCitas(){
        return ResponseEntity.ok(agendamientoServiceRequest.getAllCitas());
    }

    @GetMapping("/lista/byIdMascota/{id}")
    public ResponseEntity<?> getAllCitasByIdMascota(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamientoServiceRequest.getAllCitasByIdMascota(id));
    }

    @GetMapping("/lista/byIdPropietario/{id}")
    public ResponseEntity<?> getAllCitasByIdPropietario(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamientoServiceRequest.getAllCitasByIdPropietario(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getCitaById(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamientoServiceRequest.getCitaById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody Object cita){
        Mensaje mensaje = new Mensaje();
        try {
            mensaje = agendamientoServiceRequest.saveCita(cita);
            if(mensaje.getData() != null){
                return new ResponseEntity<>(mensaje.getData(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(mensaje.getMensaje(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody Object category){
        return new ResponseEntity<>(agendamientoServiceRequest.updateCita(id, category), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        agendamientoServiceRequest.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
