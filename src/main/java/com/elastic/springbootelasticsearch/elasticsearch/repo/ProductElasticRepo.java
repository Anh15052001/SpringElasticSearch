package com.elastic.springbootelasticsearch.elasticsearch.repo;

import static com.elastic.springbootelasticsearch.elasticsearch.ElasticConstant.ELASTIC_INDEX_PRODUCT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elastic.springbootelasticsearch.elasticsearch.document.ProductElasticDocument;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
// import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Repository
public class ProductElasticRepo {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    //CREATE OR UPDATE DOCUMENT
    public IndexResponse createOrUpdateDocument(ProductElasticDocument product) throws IOException
    {
        IndexResponse response = elasticsearchClient.index(i -> i
        .index(ELASTIC_INDEX_PRODUCT)
        .id(product.getId())
        .document(product)
        );
        return response;
    }
    //GET DOCUMENT BY ID 
    public ProductElasticDocument searchDocumentById(String productID) throws IOException 
    {
        GetResponse<ProductElasticDocument> response = elasticsearchClient.get(g -> g
            .index(ELASTIC_INDEX_PRODUCT)
            .id(productID), 
            ProductElasticDocument.class
        );
        if(response.found())
        {
            return response.source();
        }
        return null;
    }
    //DELETE DOcument by ID
    public DeleteResponse deleteDocumentById(String productID) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(ELASTIC_INDEX_PRODUCT).id(productID));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        return deleteResponse;
    }
    //SEARCH ALL DOCUMENT
    public List<ProductElasticDocument> searchAllDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index(ELASTIC_INDEX_PRODUCT));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, ProductElasticDocument.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ProductElasticDocument> products = new ArrayList<>();
        for (Hit object: hits) {
            products.add((ProductElasticDocument) object.source());
        }
        return products;
    }

}
