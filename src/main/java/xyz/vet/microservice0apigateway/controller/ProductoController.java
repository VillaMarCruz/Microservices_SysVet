package xyz.vet.microservice0apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vet.microservice0apigateway.request.InventarioServiceRequest;

@RestController
@RequestMapping("/gateway/producto")
@CrossOrigin(origins = "**")
public class ProductoController {
    @Autowired
    InventarioServiceRequest inventarioServiceRequest;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(inventarioServiceRequest.getAllProductos());
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(inventarioServiceRequest.getProductoById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProducto(@RequestBody Object producto){
        return new ResponseEntity<>(inventarioServiceRequest.saveProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable("id") Long id, @RequestBody Object producto){
        return new ResponseEntity<>(inventarioServiceRequest.updateProducto(id, producto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable("id") Long id){
        inventarioServiceRequest.deleteProducto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
