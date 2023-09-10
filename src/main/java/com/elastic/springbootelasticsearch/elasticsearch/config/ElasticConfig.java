package com.elastic.springbootelasticsearch.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class ElasticConfig {
    @Value("${application.elastic.host}")
    private String elasticHost;

    @Value("${application.elastic.port}")
    private int elasticPort;

    @Value("${application.elastic.username}")
    private String elasticUsername;

    @Value("${application.elastic.password}")
    private String elasticPassword;

    //Register the elastic search host
    @Bean
    public RestClient getRestClient() {
        //elastic config with no auth
        // RestClient restClient = RestClient.builder(new HttpHost(elasticHost, elasticPort)).build();
        //elastic config with bâsic auth
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticUsername, elasticPassword));

        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticHost, elasticPort))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        return builder.build();        
    }
    //JSON Mapper document elastic search 
    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper(null));
    }
     /**
     * There are 3 ways from which one can perform create/read/search/update/delete operations on Elasticsearch.
     * <p>
     * 1. Using ElasticsearchClient
     * 2. ElasticsearchRestTemplate
     * 3. Spring Data Repository
     * <p>
     * Note: To use advanced queries like aggregation, suggestions,
     * then it recommended to use either ElasticsearchClient
     * or ElasticsearchRestTemplate (Spring Data library provides this template).
     * <p>
     * Here I am using the Elasticsearchclient.*
     */
    @Bean
    public ElasticsearchClient gElasticsearchClient() {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }

    
}
