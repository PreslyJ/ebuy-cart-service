package com.kidz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kidz.model.Category;
import com.kidz.model.ProductTag;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long>{
	
//	@Query(value = "SELECT DISTINCT tag_contents from product_tag" , nativeQuery = true)
//	List<ProductTag> findAllTags();
}
