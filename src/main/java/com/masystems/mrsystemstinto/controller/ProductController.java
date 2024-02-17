package com.masystems.mrsystemstinto.controller;

import com.masystems.mrsystemstinto.enums.PropertyType;
import com.masystems.mrsystemstinto.model.Product;
import com.masystems.mrsystemstinto.model.Properties;
import com.masystems.mrsystemstinto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "mrsystems/tinto/api/product")
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts().orElse(null), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> findProductsByName(@PathVariable String name){
        return new ResponseEntity<>(productService.findProductsByName(name).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> newProduct(@RequestBody Product product){
        try {
            Product _product = productService
                    .newProduct(product);
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Optional<Product> isProductPresent = productService.findProduct(product.getId());
        if(isProductPresent.isPresent()){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Product _product = isProductPresent.get();
            _product.setName(product.getName());
            _product.setDescription(product.getDescription());
            _product.setImg(product.getImg());
            _product.setPrice(product.getPrice());
            _product.setActive(product.getActive());
            _product.setLastUpdate(date);
            _product.setProductProperties(product.getProductProperties());
            return new ResponseEntity<>(productService.newProduct(_product), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
