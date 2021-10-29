package com.masystems.mrsystemstinto.repository;

import com.masystems.mrsystemstinto.model.Properties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends CrudRepository<Properties, String> {
}
