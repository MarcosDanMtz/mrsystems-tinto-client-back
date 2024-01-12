package com.masystems.mrsystemstinto.repository;

import com.masystems.mrsystemstinto.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String> {
    @Query(value="SELECT * FROM clients \n" +
            "WHERE email LIKE %:id%", nativeQuery=true)
    List<Client> findClientContaining(@Param("id") String id);
}
