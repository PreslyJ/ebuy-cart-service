package com.kidz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Product;
import com.kidz.cart.model.ProductComment;

public interface ProductCommentRepository extends CrudRepository<ProductComment, Long>{
	List<ProductComment> findByProduct(Product product);
}
