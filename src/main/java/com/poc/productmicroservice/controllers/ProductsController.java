package com.poc.productmicroservice.controllers;

import com.poc.productmicroservice.dto.ProductDetailDto;
import com.poc.productmicroservice.dto.ProductTileDto;
import com.poc.productmicroservice.exception.ActionFailureException;
import com.poc.productmicroservice.models.Product;
import com.poc.productmicroservice.services.ProductService;
import com.poc.productmicroservice.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping()
public class ProductsController {

    private  final Logger logger = LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Response<ProductTileDto>> getAllProducts() {
        Response<ProductTileDto> response = new Response<>();
        response.setData(productService.getAllProducts());
        response.setSuccessMessage("Success");
        response.setStatusCode("Success");
        logger.debug(" >> ProductsController : /products : ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Response<ProductDetailDto>> getProduct(@PathVariable int id) {
        Response<ProductDetailDto> response = new Response<>();
        response.setData(Collections.singletonList(productService.getProduct(id)));
        response.setSuccessMessage("Success");
        response.setStatusCode("Success");
        logger.debug(" >> ProductsController : /products/{} call : ",id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public void addProducts(@RequestBody Product product) throws ActionFailureException {
        logger.debug(" >> ProductsController : /addProducts : ",product.toString());
        productService.createNewProduct(product);
    }


    @RequestMapping("/getProductsByCategory/{id}")
    public List<Product> getProductsByCategory(@PathVariable("id") String id) {
//        String category_id = request.get("id");
//        return productService.getProductsByCategory(category_id);
        return productService.getProductsByCategory(id);


//        return productItem;
    }
}


