package com.sebastiangelves.ss.controller;

import com.sebastiangelves.ss.model.Producto;
import com.sebastiangelves.ss.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController; 
import java.util.List; // Se necesita este import para List

@RestController //@Controller a @RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    
    // Spring se encargará de convertirla a JSON automáticamente.
    @GetMapping("/productos")
    public List<Producto> listarTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Por ahora, comentaremos el método POST para enfocarnos en que la lista cargue.
    /*
    @PostMapping("/productos")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }
    */
}