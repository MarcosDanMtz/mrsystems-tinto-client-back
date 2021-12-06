package com.masystems.mrsystemstinto.service;

import com.masystems.mrsystemstinto.enums.PropertyType;
import com.masystems.mrsystemstinto.model.Properties;
import com.masystems.mrsystemstinto.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertiesService {
    PropertiesRepository propertiesRepository;

    @Autowired
    public PropertiesService(PropertiesRepository propertiesRepository){
        this.propertiesRepository = propertiesRepository;
    }

    public Optional<List<Properties>> findAllProperties(){
        List<Properties> resList = new ArrayList<>();
        propertiesRepository.findAll().forEach(resList::add);
        return Optional.of(resList);
    }

    public Properties newProperty(Properties properties){
        return propertiesRepository.save(properties);
    }

    public Optional<Properties> findProperty(String id){
        return propertiesRepository.findById(id);
    }

    public List<Properties> findByPropertyTypeAndName(PropertyType property, String name, String productId){
        return propertiesRepository.findPropertyByPropertyTypeIdAndName(property.getValue(), name, productId);
    }
}
