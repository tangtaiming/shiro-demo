package com.application.ttm.service;

import com.application.ttm.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-05</p>
 * <p>@Version 1.0</p>
 **/
public interface ProductService extends PageService {

    Product createProduct(Product product);
    Product updateProduct(Product product);
    boolean deleteProduct(Long id);

    List<Product> findProductList(Map<String, Object> params);
    Product findProduct(Long id);


}
