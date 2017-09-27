package com.kidz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kidz.model.Category;
import com.kidz.model.Item;
import com.kidz.model.Product;
import com.kidz.model.SubCategory;
import com.kidz.service.CategoryService;
import com.kidz.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService; 
	
	@RequestMapping(value="/saveProduct",method=RequestMethod.PUT)
	@CrossOrigin
	public Product saveProduct(@RequestBody Product product) {

		productService.save(product);

		return product;
		
	}

	@RequestMapping(value="/getProductById/{productId}",method=RequestMethod.GET)
	@CrossOrigin
	public Product getProductById(@PathVariable Long productId) {

		return productService.getProductById(productId);

	}
	
	@RequestMapping(value="/deleteProduct/{productId}",method=RequestMethod.DELETE)
	@CrossOrigin
	public void deleteProduct(@PathVariable Long productId) {

		productService.delete(productId);
		
	}
	
	@RequestMapping(value="/getAllProducts",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Product> getAllProducts(Pageable pageable) {

		return productService.getAllProducts(pageable,null);

	}
	
	
	@RequestMapping(value="/saveCategory",method=RequestMethod.PUT)
	@CrossOrigin
	public Category saveCategory(@RequestBody Category category) {

		categoryService.save(category);

		return category;
		
	}

	@RequestMapping(value="/getCategoryById/{categoryId}",method=RequestMethod.GET)
	@CrossOrigin
	public Category getCategoryById(@PathVariable Long categoryId) {

		return categoryService.getCategoryById(categoryId);

	}
	
	@RequestMapping(value="/deleteCategory/{categoryId}",method=RequestMethod.DELETE)
	@CrossOrigin
	public void deleteCategory(@PathVariable Long categoryId) {

		categoryService.delete(categoryId);
		
	}
	
	@RequestMapping(value="/getAllCategories",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Category> getAllCategories(Pageable pageable) {

		return categoryService.getAllCategory(pageable,null);

	}
	
	@RequestMapping(value="/saveSubCategory",method=RequestMethod.PUT)
	@CrossOrigin
	public SubCategory saveSubCategory(@RequestBody SubCategory category) {

		categoryService.saveSubCategory(category);

		return category;
		
	}

	@RequestMapping(value="/getSubCategoryById/{subCategoryId}",method=RequestMethod.GET)
	@CrossOrigin
	public SubCategory getSubCategoryById(@PathVariable Long subCategoryId) {

		return categoryService.getSubCategoryById(subCategoryId);

	}
	
	@RequestMapping(value="/deleteSubCategory/{subCategoryId}",method=RequestMethod.DELETE)
	@CrossOrigin
	public void deleteSubCategory(@PathVariable Long subCategoryId) {

		categoryService.deleteSubCategory(subCategoryId);
		
	}
	
	@RequestMapping(value="/getAllSubCategories",method=RequestMethod.POST)
	@CrossOrigin

	public Page<SubCategory> getAllSubCategories(Pageable pageable) {
				

		return categoryService.getAllSubCategory(pageable,null);

	}
	
	@RequestMapping(value="/saveItem",method=RequestMethod.PUT)
	@CrossOrigin
	public void saveItem(@RequestBody Item item) {

		productService.saveItem(item);
		
	}

	@RequestMapping(value="/getItemById/{itemId}",method=RequestMethod.GET)
	@CrossOrigin
	public Item getItemById(@PathVariable Long itemId) {

		return productService.getItemById(itemId);

	}
	
	@RequestMapping(value="/deleteItem/{itemId}",method=RequestMethod.DELETE)
	@CrossOrigin
	public void deleteItem(@PathVariable Long itemId) {

		productService.deleteItem(itemId);
		
	}
	
	@RequestMapping(value="/getAllItems",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Item> getAllItems(Pageable pageable) {

		return productService.getAllItems(pageable,null);

	}
	
	@RequestMapping(value = "/uploadItemImage", method = RequestMethod.POST)
	@CrossOrigin
	public Map<String,String> singleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam() long itemId) throws IOException {

		Map<String,String> result=new HashMap();
		
		if (file.isEmpty()){ 		
			result.put("message","Please select a image to upload");
			
			return result;
		}	
			

		Item item=productService.getItemById(itemId);
		
		item.setImage(file.getBytes());
		
		productService.saveItem(item);
		
		result.put("message","Image uploaded successfully");
		
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/getImageByTitleId/{itemId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageByTitleId(@PathVariable long itemId){
		
		 Item item=productService.getItemById(itemId);
		 
		 return item.getImage();
		 
	}
	
}
