package com.masystems.mrsystemstinto.repository;

import com.masystems.mrsystemstinto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value="SELECT * FROM product P WHERE P.name LIKE %:name%", nativeQuery=true)
    List<Product> findProductsByName(@Param("name") String name);

}
