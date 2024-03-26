package com.masystems.mrsystemstinto.repository;

import com.masystems.mrsystemstinto.enums.PropertyType;
import com.masystems.mrsystemstinto.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, String> {

    @Query(value="SELECT * FROM properties prty where prty.property_type_id = :property AND\n" +
            "prty.id NOT IN (\n" +
            "SELECT p.id FROM product_properties pp\n" +
            "INNER JOIN product pr ON pr.id = pp.product_id\n" +
            "INNER JOIN properties p ON p.id = pp.properties_id\n" +
            "WHERE pr.id = :productId)", nativeQuery=true)
    List<Properties> findPropertyByPropertyTypeId(@Param("property") int property,
                                                  @Param("productId") String productId);

    @Query(value="SELECT * FROM properties prty where prty.property_type_id = :property AND " +
            "prty.name LIKE %:name% AND " +
            "prty.id NOT IN (" +
            "SELECT p.id FROM product_properties pp " +
            "INNER JOIN product pr ON pr.id = pp.product_id " +
            "INNER JOIN properties p ON p.id = pp.properties_id " +
            "WHERE pr.id = :productId)", nativeQuery=true)
    List<Properties> findPropertyByPropertyTypeIdAndName(@Param("property") int property,
                                                         @Param("name") String name,
                                                         @Param("productId") String productId);

    @Query(value="SELECT * FROM properties p \n" +
            "INNER JOIN product_properties pp ON pp.properties_id  = p.id \n" +
            "INNER JOIN product pr ON pr.id = pp.product_id \n" +
            "WHERE  p.property_type_id = :property \n" +
            "AND pr.id = :productId", nativeQuery=true)
    List<Properties> findPropertiesByTypeAndProductId(@Param("property") int property,
                                                         @Param("productId") String productId);

    @Query(value="SELECT * FROM properties p \n" +
            "WHERE p.property_type_id = :property", nativeQuery=true)
    List<Properties> findProperiesByType(@Param("property") int property);

    @Query(value="SELECT * FROM properties p\n" +
            "WHERE  p.property_type_id = :property\n" +
            "AND p.name LIKE %:name%", nativeQuery=true)
    List<Properties> findProperiesByTypeAndName(@Param("property") int property,
                                         @Param("name") String name);

    /*@Query(value="SELECT * FROM properties p \n" +
            "WHERE p.id = :propertyId;", nativeQuery=true)
    Properties findPropertyById(@Param("propertyId") String propertyId);*/
}
