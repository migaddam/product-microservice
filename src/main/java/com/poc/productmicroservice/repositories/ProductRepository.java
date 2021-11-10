
package com.poc.productmicroservice.repositories;

import com.poc.productmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    @Query("SELECT p FROM Product p WHERE p.stock>0")
    public List<Product> findAll();

    @Query("Select pro FROM Product pro WHERE pro.categoryId=:cat_id")
    List<Product> getByCategoryId(@Param("cat_id")String cat_id);

}
