package com.poc.productmicroservice.services;

import com.poc.productmicroservice.dto.ProductDetailDto;
import com.poc.productmicroservice.dto.ProductTileDto;
import com.poc.productmicroservice.exception.ActionFailureException;
import com.poc.productmicroservice.models.Product;
import com.poc.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;


    @CachePut(value = "getAllProduct")
    public List<ProductTileDto> getAllProducts() {
        logger.debug(" >> ProductService : Entering getAllProducts");
        logger.debug(" >> ProductService : Exiting getAllProducts");
        List<Product> productList = productRepository.findAll();
        return productList.stream().
                map((product -> new ProductTileDto(product.getId(), product.getName(), product.getPricePerUnit(), product.getImageUrl(), product.getOriginalPricePerUnit())))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "getProduct",key = "#id")
    public ProductDetailDto getProduct(int id){
        logger.debug(" >> ProductService : Entering getProduct");

        Product product = productRepository.findById(id).orElse(null);
        if(product==null) return null;
        return new ProductDetailDto(product.getId(), product.getName(), product.getDescription(), product.getPricePerUnit(), product.getImageUrl(), product.getOriginalPricePerUnit());
    }

    public void createNewProduct(Product product) throws ActionFailureException {

        logger.debug(" >> ProductService : Entering createNewProduct");
        product.setName(product.getName());
        product.setImageUrl(product.getImageUrl());
        product.setOriginalPricePerUnit(product.getOriginalPricePerUnit());
        product.setPricePerUnit(product.getPricePerUnit());

        productRepository.save(product);
        logger.debug(" >> ProductService : Exiting createNewProduct");

    }

    public List<Product>getProductsByCategory(String product_id){
        return productRepository.getByCategoryId(product_id);
    }



}
