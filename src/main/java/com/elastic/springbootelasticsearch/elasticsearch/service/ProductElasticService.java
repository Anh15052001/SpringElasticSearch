package com.elastic.springbootelasticsearch.elasticsearch.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elastic.springbootelasticsearch.elasticsearch.dto.ProductElasticDTO;

@Service
public interface ProductElasticService {
     public ResponseEntity saveOrUpdate(ProductElasticDTO requestDTO) throws IOException;

     public ResponseEntity deleteById(String id) throws IOException;

     public ResponseEntity searchById(String id) throws IOException;

     public ResponseEntity searchAll() throws IOException;

}
