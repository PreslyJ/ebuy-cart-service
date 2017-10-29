package com.kidz.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kidz.cart.model.Item;
import com.kidz.cart.model.QItem;
import com.kidz.repository.ItemRepository;
import com.querydsl.core.BooleanBuilder;

@Service
@Transactional
class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Page<Item> getAllItems(Pageable pageable, Map<String, Object> filterMap) {
		if(filterMap==null)
			return itemRepository.findAll(pageable);
		
		boolean isRecomended=filterMap.get("isRecomended")!=null?Boolean.parseBoolean( filterMap.get("isRecomended").toString()):false;
		boolean isFeatured=filterMap.get("isFeatured")!=null?Boolean.parseBoolean( filterMap.get("isFeatured").toString()):false;
		long subCategoryId=filterMap.get("subCategoryId")==null?0:Long.valueOf(filterMap.get("subCategoryId").toString());
		String status=filterMap.get("status")==null?null:filterMap.get("status").toString();	
		
		QItem item=QItem.item;
		
		BooleanBuilder whereClause = new BooleanBuilder();

		if(filterMap.get("isFeatured")!=null)
			whereClause.and(item.isFeatured.eq(isFeatured));
		
		if(filterMap.get("isRecomended")!=null)
			whereClause.and(item.isRecomended.eq(isRecomended));
		
		if(subCategoryId>0)
			whereClause.and(item.subCategory.id.eq(subCategoryId));
			
		if(status!=null)
			whereClause.and(item.status.in(status));
			
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
