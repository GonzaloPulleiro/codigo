package edu.tienda.core.services;


import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("BD")
@ConditionalOnProperty(value = "productos.estrategia", havingValue = "EN_BD")
public class ProductosServiceBDImpl implements ProductoService{

    @Autowired
    private ProductosRepository productosRepository;

    /*
    public List<Producto> getProductos(){

        List<ProductoEntity> productosEntities = productosRepository.findAll();
        List<Producto> productos = new ArrayList<>();

        for(ProductoEntity productEntity : productosEntities){
            Producto producto = new Producto();
            producto.setId(productEntity.getId());
            producto.setNombre(productEntity.getNombre());
            producto.setPrecio(productEntity.getPrecio());
            producto.setStock(productEntity.getStock());
            productos.add(producto);
        }
        return productos;
    }

    */

    /*
    public List<Producto> getProductos(){

        List<Producto> productos = productosRepository.findByPrecioLessThan(1000.0).stream()
                .map(productoEntity -> {
                    Producto producto = new Producto();
                    producto.setId(productoEntity.getId());
                    producto.setNombre(productoEntity.getNombre());
                    producto.setPrecio(productoEntity.getPrecio());
                    producto.setStock(productoEntity.getStock());
                    return producto;
                }).collect(Collectors.toList());
        return productos;
    }

     */

    /*
    public List<Producto> getProductos(){

        List<Producto> productos = productosRepository.findByNombreLike("Teclado").stream()
                .map(productoEntity -> {
                    Producto producto = new Producto();
                    producto.setId(productoEntity.getId());
                    producto.setNombre(productoEntity.getNombre());
                    producto.setPrecio(productoEntity.getPrecio());
                    producto.setStock(productoEntity.getStock());
                    return producto;
                }).collect(Collectors.toList());
        return productos;
    }
     */
    public List<Producto> getProductos(){

        List<Producto> productos = productosRepository.findByPrecioGreaterThanAndStockLessThan(1000.0, 10).stream()
                .map(productoEntity -> {
                    Producto producto = new Producto();
                    producto.setId(productoEntity.getId());
                    producto.setNombre(productoEntity.getNombre());
                    producto.setPrecio(productoEntity.getPrecio());
                    producto.setStock(productoEntity.getStock());
                    return producto;
                }).collect(Collectors.toList());
        return productos;
    }


    @Override
    public void saveProducto(Producto producto) {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(producto.getNombre());
        productoEntity.setPrecio(producto.getPrecio());
        productoEntity.setStock(producto.getStock());

        productosRepository.save(productoEntity);
    }


}
