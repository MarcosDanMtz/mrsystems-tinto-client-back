package com.masystems.mrsystemstinto.service;

import com.masystems.mrsystemstinto.model.Product;
import com.masystems.mrsystemstinto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Optional<List<Product>> findAllProducts(){
        List<Product> resultList = new ArrayList<>();
        productRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    public Optional<List<Product>> findProductsByName(String name){
        List<Product> resultList = new ArrayList<>();
        productRepository.findProductsByName(name).forEach(resultList::add);
        return Optional.of(resultList);
    }

    public Product newProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(String id){
        return productRepository.findById(id);
    }

    public Optional<Product> findProduct(String id){
        return productRepository.findById(id);
    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }
}
