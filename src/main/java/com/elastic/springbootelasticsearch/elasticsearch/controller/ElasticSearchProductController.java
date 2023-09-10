package com.elastic.springbootelasticsearch.elasticsearch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.springbootelasticsearch.common.controller.Response;
import com.elastic.springbootelasticsearch.elasticsearch.dto.ProductElasticDTO;
import com.elastic.springbootelasticsearch.elasticsearch.service.ProductElasticService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/elastic/product")
public class ElasticSearchProductController {
    @Autowired
    ProductElasticService productElasticService;
    
    @PostMapping(value = "/saveupdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> saveOrUpdate(@RequestBody ProductElasticDTO request) throws IOException
    {
        System.out.println("test");
        return productElasticService.saveOrUpdate(request);

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable String id) throws IOException {
        return productElasticService.deleteById(id);
    }
    @GetMapping(value="/search/{id}")
    public ResponseEntity<Response> searchById(@PathVariable String id) throws IOException{
        return productElasticService.searchById(id);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<Response> searchAll() throws IOException {
        return productElasticService.searchAll();
    }
    
}
