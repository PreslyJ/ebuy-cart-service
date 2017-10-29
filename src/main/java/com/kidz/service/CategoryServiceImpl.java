package com.kidz.service;

import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kidz.cart.model.Category;
import com.kidz.cart.model.QCategory;
import com.kidz.cart.model.QSubCategory;
import com.kidz.cart.model.SubCategory;
import com.kidz.repository.CategoryRepository;
import com.kidz.repository.SubCategoryRepository;
import com.querydsl.core.BooleanBuilder;

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
		
		String status=filterMap.get("status")==null?null:filterMap.get("status").toString();	
		String name=filterMap.get("name")==null?null:filterMap.get("name").toString();	
		
		
		QCategory category=QCategory.category;
		
		BooleanBuilder whereClause = new BooleanBuilder();
		
		if(status!=null)
			whereClause.and(category.status.in(status));
		
		if(name!=null)
			whereClause.and(category.name.in(name));
		
		
		return categoryRepostitory.findAll(whereClause,pageable);
		
	}

	@Override
	public Page<SubCategory> getAllSubCategory(Pageable pageable, Map<String, Object> filterMap) {
		
		String status=filterMap.get("status")==null?null:filterMap.get("status").toString();	
		String name=filterMap.get("name")==null?null:filterMap.get("name").toString();	
		long categoryId=filterMap.get("categoryId")==null?0:Long.valueOf(filterMap.get("categoryId").toString());
		

		QSubCategory subCategory=QSubCategory.subCategory;
		
		BooleanBuilder whereClause = new BooleanBuilder();
		
		if(status!=null)
			whereClause.and(subCategory.status.in(status));
		
		if(name!=null)
			whereClause.and(subCategory.name.in(name));
		
		if(categoryId>0)
			whereClause.and(subCategory.category.Id.eq(categoryId));
			
		return subCategoryRepository.findAll(whereClause,pageable);
		
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
