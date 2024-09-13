package me.inventarios.servicio;

import me.inventarios.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProductos();
    public Producto buscarProductoPorId(Integer id);
    public Producto guardarProducto(Producto producto);

    public void eliminarProducto(Integer id);


}
