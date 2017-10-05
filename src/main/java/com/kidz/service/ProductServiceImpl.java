package com.kidz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidz.cart.model.Category;
import com.kidz.cart.model.Item;
import com.kidz.cart.model.Product;
import com.kidz.cart.model.QItem;
import com.kidz.repository.ItemRepository;
import com.kidz.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;

@Service
@Transactional
class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public List<Product> getProductsByCategory(Category category){
		return null;
		//return productRepository.findAllByProductCategory(category);
	}
	
	public Page<Product> getAllProducts(Pageable pageable,Map<String, Object> filterMap){
		return productRepository.findAll(pageable);
	}
	
	public Product getProductById(Long productId){
		return productRepository.findOne(productId);
	}
	public void save(Product product){
		productRepository.save(product);
	}
	
	public void delete(Long productId){
		productRepository.delete(productId);
	}

	@Override
	public Page<Item> getAllItems(Pageable pageable, Map<String, Object> filterMap) {
		if(filterMap==null)
			return itemRepository.findAll(pageable);
		
		boolean isRecomended=filterMap.get("isRecomended")!=null?Boolean.parseBoolean( filterMap.get("isRecomended").toString()):false;
		boolean isFeatured=filterMap.get("isFeatured")!=null?Boolean.parseBoolean( filterMap.get("isFeatured").toString()):false;
		
		QItem item=QItem.item;
		
		BooleanBuilder whereClause = new BooleanBuilder();

		if(filterMap.get("isFeatured")!=null)
			whereClause.and(item.isFeatured.eq(isFeatured));
		
		if(filterMap.get("isRecomended")!=null)
			whereClause.and(item.isRecomended.eq(isRecomended));
		
		return itemRepository.findAll(whereClause,pageable);
		
	}

	@Override
	public Item getItemById(Long itemId) {
		return itemRepository.getOne(itemId);
	}

	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public void deleteItem(Long itemId) {
		itemRepository.delete(itemId);
	}
}
