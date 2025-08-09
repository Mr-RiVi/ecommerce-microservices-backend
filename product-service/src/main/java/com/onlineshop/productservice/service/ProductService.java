package com.onlineshop.productservice.service;

import com.onlineshop.productservice.dto.ProductRequest;
import com.onlineshop.productservice.dto.ProductResponse;
import com.onlineshop.productservice.model.Product;
import com.onlineshop.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    //For access productRepository we want to inject ProductRepository class to our service
    //For that we use constructor injection
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest){

        //Create instance using all requested product details
        //Mapping dto to model
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        //Saving our instance inside Database
        productRepository.save(product);
        log.info("product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
