package com.elastic.springbootelasticsearch.elasticsearch.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductElasticDTO {
    String id;
    String name;
    String description;
    Double price;
}
