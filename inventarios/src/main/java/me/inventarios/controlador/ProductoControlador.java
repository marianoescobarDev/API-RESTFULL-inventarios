package me.inventarios.controlador;

import me.inventarios.excepcion.RecursoNoEncontradoException;
import me.inventarios.modelo.Producto;
import me.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value="http://localhost:4200")
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;


    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto>productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: " +producto);
        this.productoServicio.guardarProducto(producto);
        return producto;
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto>obtenerProductoPorId(
            @PathVariable int id
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);

        if(producto!=null){
            return ResponseEntity.ok(producto);
        }else {
            throw new RecursoNoEncontradoException("No se encontro el Id");
        }


    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto>modificarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido){

        Producto productoModificado = this.productoServicio.buscarProductoPorId(id);
        productoModificado.setDescripcion(productoRecibido.getDescripcion());
        productoModificado.setCantidad(productoRecibido.getCantidad());
        productoModificado.setPrecio(productoRecibido.getPrecio());

        this.productoServicio.guardarProducto(productoModificado);

        return ResponseEntity.ok(productoModificado);

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String,Boolean>>eliminarProducto(@PathVariable int id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if(producto==null){
            throw new RecursoNoEncontradoException("No se encontro el id");
        }
        this.productoServicio.eliminarProducto(producto.getId());
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado",Boolean.TRUE);

        return ResponseEntity.ok(respuesta);

    }



}
