package com.masystems.mrsystemstinto.controller;

import com.masystems.mrsystemstinto.enums.PropertyType;
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
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/property/type")
    public ResponseEntity<List<Properties>> findPropertiesByTypeAndNameAndProductId(@RequestParam PropertyType propertyType,
                                                                        @RequestParam(required = false) String name,
                                                                        @RequestParam String productId){
        return new ResponseEntity<>(propertiesService.findByPropertyTypeAndName(propertyType, name, productId), HttpStatus.OK);
    }

    @GetMapping("/type/{propertyType}/product/{productId}")
    public  ResponseEntity<List<Properties>> findPropertiesByTypeAndProductId(@PathVariable PropertyType propertyType,
                                                                              @PathVariable String productId){
        return new ResponseEntity<>(propertiesService.findPropertiesByTypeAndProductId(propertyType, productId), HttpStatus.OK);
    }

    @GetMapping("/type/{propertyType}")
    public  ResponseEntity<List<Properties>> findPropertiesByType(@PathVariable PropertyType propertyType){
        return new ResponseEntity<>(propertiesService.findPropertiesByType(propertyType), HttpStatus.OK);
    }

    @GetMapping("/type/{propertyType}/{name}")
    public  ResponseEntity<List<Properties>> findPropertiesByTypeAndName(@RequestParam PropertyType propertyType,
                                                                         @RequestParam String name){
        return new ResponseEntity<>(propertiesService.findByPropertyByTypeAndName(propertyType, name), HttpStatus.OK);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Properties> findPropertiesById(@PathVariable String propertyId){
        return new ResponseEntity<>(propertiesService.findProperty(propertyId).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePropertyById(@PathVariable("id") String id){
        propertiesService.deletePropById(id);
        return ResponseEntity.ok("Property delete correctly " + id);
    }

    @DeleteMapping("/ids")
    public ResponseEntity<String> deletePropertiesById(@RequestBody List<String> propIds){
        propertiesService.deletePropertiesById(propIds);
        return ResponseEntity.ok("Property delete correctly " + propIds);
    }
}
 