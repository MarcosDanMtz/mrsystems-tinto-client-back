package com.masystems.mrsystemstinto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ProductProperties implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    private List<Product> productList;

    @ManyToOne(cascade = CascadeType.ALL)
    private List<Properties> propertiesList;
}
