package me.inventarios.servicio;

import me.inventarios.modelo.Producto;
import me.inventarios.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoServicio implements IProductoServicio{

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer id) {
        Producto producto = this.productoRepositorio.findById(id).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Integer id) {
        this.productoRepositorio.deleteById(id);
    }
}
