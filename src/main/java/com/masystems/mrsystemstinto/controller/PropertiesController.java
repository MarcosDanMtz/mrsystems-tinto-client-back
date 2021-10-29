package com.masystems.mrsystemstinto.controller;

import com.masystems.mrsystemstinto.model.Properties;
import com.masystems.mrsystemstinto.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "mrsystems/tinto/api/properties")
@Controller
public class PropertiesController {
    private PropertiesService propertiesService;

    @Autowired
    public PropertiesController(PropertiesService propertiesService){
        this.propertiesService = propertiesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Properties>> getAllProperties(){
        return new ResponseEntity<>(propertiesService.findAllProperties().orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Properties> newProperty(@RequestBody Properties propertiesToSave){
        try {
            Properties _properties = propertiesService
                    .newProperty(propertiesToSave);
            return new ResponseEntity<>(_properties, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Properties> replaceProperty(@RequestBody Properties properties){
        Optional<Properties> propertiesToUpdate = propertiesService.findProperty(properties.getId());

        if (propertiesToUpdate.isPresent()) {
            Properties _properties = propertiesToUpdate.get();
            _properties.setName(properties.getName());
            _properties.setDescription(properties.getDescription());
            _properties.setImg(properties.getImg());
            _properties.setPropertyTypeId(properties.getPropertyTypeId());
            _properties.setAddPrice(properties.getAddPrice());
            return new ResponseEntity<>(propertiesService.newProperty(_properties), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
 