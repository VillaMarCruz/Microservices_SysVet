package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.InventarioServiceRequest;

@RestController
@RequestMapping("/gateway/proveedor-producto")
@CrossOrigin(origins = "**")
public class ProveedorProductoController {
    @Autowired
    InventarioServiceRequest inventarioServiceRequest;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllProveedores(){
        return ResponseEntity.ok(inventarioServiceRequest.getAllProveedores());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getProveedorById(@PathVariable("id") Long id){
        return ResponseEntity.ok(inventarioServiceRequest.getProveedorById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProveedor(@RequestBody Object proveedor){
        return new ResponseEntity<>(inventarioServiceRequest.saveProveedor(proveedor), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable("id") Long id, @RequestBody Object proveedor){
        return new ResponseEntity<>(inventarioServiceRequest.updateProveedor(id, proveedor), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable("id") Long id){
        inventarioServiceRequest.deleteProveedor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
