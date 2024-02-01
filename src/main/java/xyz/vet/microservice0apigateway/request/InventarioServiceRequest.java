package xyz.vet.microservice0apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "inventario-service",
        path = "/api/",
        url = "${inventario.service.url}",
        configuration = FeignConfiguration.class
)
public interface InventarioServiceRequest {

    // CATEGORIA DE PRODUCTO
    @PostMapping("categoria-producto/create")
    Object saveCategoria(@RequestBody Object requestBody);

    @PutMapping("categoria-producto/update/{id}")
    Object updateCategoria(@PathVariable("id") Long id, @RequestBody Object requestBody);

    @DeleteMapping("categoria-producto/delete/{id}")
    void deleteCategoria(@PathVariable("id") Long id);

    @GetMapping("categoria-producto/lista")
    List<Object> getAllCategorias();

    @GetMapping("categoria-producto/detail/{id}")
    Object getById(@PathVariable("id") Long id);

    //PRESENTACION DE PRODUCTO

    @PostMapping("presentacion-producto/create")
    Object savePresentacion(@RequestBody Object requestBody);

    @PutMapping("presentacion-producto/update/{id}")
    Object updatePresentacion(@PathVariable("id") Long id, @RequestBody Object requestBody);

    @DeleteMapping("presentacion-producto/delete/{id}")
    void deletePresentacion(@PathVariable("id") Long id);

    @GetMapping("presentacion-producto/lista")
    List<Object> getAllPresentaciones();

    @GetMapping("presentacion-producto/detail/{id}")
    Object getPresentacionById(@PathVariable("id") Long id);

    // PROVEEDOR DE PRODUCTO

    @PostMapping("proveedor-producto/create")
    Object saveProveedor(@RequestBody Object requestBody);

    @PutMapping("proveedor-producto/update/{id}")
    Object updateProveedor(@PathVariable("id") Long id, @RequestBody Object requestBody);

    @DeleteMapping("proveedor-producto/delete/{id}")
    void deleteProveedor(@PathVariable("id") Long id);

    @GetMapping("proveedor-producto/lista")
    List<Object> getAllProveedores();

    @GetMapping("proveedor-producto/detail/{id}")
    Object getProveedorById(@PathVariable("id") Long id);

    // PRODUCTO

    @PostMapping("producto/create")
    Object saveProducto(@RequestBody Object requestBody);

    @PutMapping("producto/update/{id}")
    Object updateProducto(@PathVariable("id") Long id, @RequestBody Object requestBody);

    @DeleteMapping("producto/delete/{id}")
    void deleteProducto(@PathVariable("id") Long id);

    @GetMapping("producto/lista")
    List<Object> getAllProductos();

    @GetMapping("producto/detail/{id}")
    Object getProductoById(@PathVariable("id") Long id);

}
