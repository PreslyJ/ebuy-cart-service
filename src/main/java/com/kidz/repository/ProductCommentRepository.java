package com.kidz.repository;


import org.springframework.data.repository.CrudRepository;
import com.kidz.cart.model.ProductComment;

public interface ProductCommentRepository extends CrudRepository<ProductComment, Long>{
}
