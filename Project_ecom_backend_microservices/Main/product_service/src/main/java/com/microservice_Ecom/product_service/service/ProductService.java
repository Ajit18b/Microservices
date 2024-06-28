package com.microservice_Ecom.product_service.service;

import com.microservice_Ecom.product_service.dto.ProductRequest;
import com.microservice_Ecom.product_service.dto.ProductResponse;
import com.microservice_Ecom.product_service.model.Product;
import com.microservice_Ecom.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description((productRequest.getDescription()))
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products  = productRepository.findAll();
        return products.stream().map(this::mapTpProductResponse).toList();
    }

    private ProductResponse mapTpProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
