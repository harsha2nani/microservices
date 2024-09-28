package com.demo.products.Repository;

import com.demo.products.DAO.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductDAO,Integer> {

}
