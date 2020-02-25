package com.kristian.test.repository;

import com.kristian.test.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductModel, Long> {

    @Query(value = "SELECT * FROM products WHERE to_date IS NULL", nativeQuery = true)
    List<ProductModel> findAllProducts();
}
