package com.kidz.service;

import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kidz.model.Category;
import com.kidz.model.SubCategory;
import com.kidz.repository.CategoryRepository;
import com.kidz.repository.SubCategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepostitory;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public void save(Category category){
		categoryRepostitory.save(category);
	}
	
	public void delete(Long categoryId){
		categoryRepostitory.delete(categoryId);
	}
	
	public Category getCategoryById(Long categoryId){
		return categoryRepostitory.findOne(categoryId);
	}

	@Override
	public Page<Category> getAllCategory(Pageable pageable, Map<String, Object> filterMap) {
		return categoryRepostitory.findAll(pageable);
	}

	@Override
	public Page<SubCategory> getAllSubCategory(Pageable pageable, Map<String, Object> filterMap) {
		return subCategoryRepository.findAll(pageable);
	}

	@Override
	public void saveSubCategory(SubCategory category) {
		subCategoryRepository.save(category);
	}

	@Override
	public void deleteSubCategory(Long subCategoryId) {
		subCategoryRepository.delete(subCategoryId);
	}

	@Override
	public SubCategory getSubCategoryById(Long subCategoryId) {
		return subCategoryRepository.findOne(subCategoryId);
	}
	
}
