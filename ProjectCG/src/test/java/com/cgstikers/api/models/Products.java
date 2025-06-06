package com.cgstikers.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Boolean isActive;
    private Integer productTypeId;
    private List<Dimension> dimensions;
    private List<Discount> discounts;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dimension {
        private Integer width;
        private Integer height;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Discount {
        private Integer quantity;
        private Integer percentage;
    }
}
