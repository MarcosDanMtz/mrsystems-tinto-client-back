package com.masystems.mrsystemstinto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masystems.mrsystemstinto.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Properties")
public class Properties {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    //@GeneratedValue
    private String id;
    private String name;
    private String description;
    private String img;
    @Enumerated(EnumType.ORDINAL)
    private PropertyType propertyTypeId;
    private BigDecimal addPrice;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade =
            {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST,
                CascadeType.REMOVE
            },
            mappedBy = "productProperties")
    @JsonBackReference
    List<Product> products;
}
