package com.kidz.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kidz.model.Category;
import com.kidz.model.SubCategory;

public interface CategoryService {
	
	Page<Category> getAllCategory(Pageable pageable,Map<String, Object> filterMap);

	void save(Category category);
	
	void delete(Long categoryId);
	
	Category getCategoryById(Long categoryId);
	
	Page<SubCategory> getAllSubCategory(Pageable pageable,Map<String, Object> filterMap);

	void saveSubCategory(SubCategory category);
	
	void deleteSubCategory(Long subCategoryId);
	
	SubCategory getSubCategoryById(Long subCategoryId);
}
