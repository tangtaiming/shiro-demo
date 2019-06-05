package com.application.ttm.service.impl;

import com.application.ttm.PageUtils;
import com.application.ttm.dao.ProductDao;
import com.application.ttm.entity.Product;
import com.application.ttm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 业务类
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-05</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product createProduct(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = findProduct(id);
        if (null == product) {
            return false;
        } else {
            return productDao.delete(product);
        }
    }

    @Override
    public List<Product> findProductList(Map<String, Object> params) {
        Integer pageSize = getNumPerPage(params);
        Integer first = getPageNum(params) - 1 * pageSize;

        return productDao.findList(first, pageSize);
    }

    @Override
    public Product findProduct(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }

}
