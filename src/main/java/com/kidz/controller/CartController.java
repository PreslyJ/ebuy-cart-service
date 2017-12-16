package com.kidz.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.kidz.cart.model.Cart;
import com.kidz.cart.model.CartItem;
import com.kidz.cart.model.Category;
import com.kidz.cart.model.Customer;
import com.kidz.cart.model.Item;
import com.kidz.cart.model.PurchasedItems;
import com.kidz.cart.model.StockItems;
import com.kidz.cart.model.SubCategory;
import com.kidz.service.CartService;
import com.kidz.service.CategoryService;
import com.kidz.service.CustomerService;
import com.kidz.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	CustomerService customerService; 
	
	@Autowired
	CartService cartServive;
	
	
	
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

		Category category=categoryService.getCategoryById(categoryId);
		category.setStatus("Inactive");
		
		categoryService.save(category);
		
	}
	
	@RequestMapping(value="/getAllCategories",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Category> getAllCategories(Pageable pageable,@RequestBody Map<String, Object> filterMap) {

		return categoryService.getAllCategory(pageable,filterMap);

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

		SubCategory subCategory=categoryService.getSubCategoryById(subCategoryId);
		subCategory.setStatus("Inactive");
		
		categoryService.saveSubCategory(subCategory);
		
	}
	
	@RequestMapping(value="/getAllSubCategories",method=RequestMethod.POST)
	@CrossOrigin
	public Page<SubCategory> getAllSubCategories(Pageable pageable,@RequestBody Map<String, Object> filterMap) {
				
		return categoryService.getAllSubCategory(pageable,filterMap);

	}
	
	@RequestMapping(value="/saveItem",method=RequestMethod.PUT)
	@CrossOrigin
	public Item saveItem(@RequestBody Item item) {

		return productService.saveItem(item);
		
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
	/*	item.setStatus("Inactive");
		
		productService.saveItem(item);
		*/
	}
	
	@RequestMapping(value="/getAllItems",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Item> getAllItems(Pageable pageable,@RequestBody Map<String, Object> filterMap) {

		return productService.getAllItems(pageable,filterMap);

	}
	
	@RequestMapping(value = "/uploadItemImage", method = RequestMethod.POST)
	@CrossOrigin
	public Map<String,String> uploadItemImage(@RequestParam("file") MultipartFile file,@RequestParam() long itemId) throws IOException {

		Map<String,String> result=new HashMap<String, String>();
		
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
		 
		 return scale(item.getImage(), 268, 249);
		 
	}
	
	@RequestMapping(value="/filterItems",method=RequestMethod.POST)
	@CrossOrigin
	public Page<Item> filterItems(Pageable pageable,@RequestBody Map<String, Object> filterMap) {

		return productService.getAllItems(pageable,filterMap);

	}
	
	
	@RequestMapping(value="/addToCart",method=RequestMethod.POST)
	@CrossOrigin
	public void addTocart(Pageable pageable,@RequestBody Map<String, Object> reqMap) {

		long customerId=reqMap.get("customerId")!=null?Long.valueOf((String)reqMap.get("customerId")):0;
		long itemId=reqMap.get("itemId")!=null?Long.valueOf((Integer)reqMap.get("itemId")):0;
		int quantity=reqMap.get("quantity")!=null?Integer.valueOf((String)reqMap.get("quantity")):1;
		
		Customer customer=customerService.getCustomerById(customerId);
		
		Item item=productService.getItemById(itemId);
		
		CartItem cItem=new CartItem();
		
		cItem.setQuantity(quantity);
		cItem.setTotalPriceDouble(item.getPrice()*quantity);
		cItem.setItem(item);
		
		Cart cart=customer.getCart();
		cart.setGrandTotal(item.getPrice()*quantity+cart.getGrandTotal());
		
		cItem.setCart(cart);
		
		cartServive.saveCartItem(cItem);
	}

	
	@RequestMapping(value="/getCart",method=RequestMethod.POST)
	@CrossOrigin
	public Cart getcart(Pageable pageable,@RequestBody Map<String, Object> reqMap) {

		long customerId=reqMap.get("customerId")!=null?Long.valueOf((String)reqMap.get("customerId")):0;
		
		Customer customer=customerService.getCustomerById(customerId);
		
		return customer.getCart();
		
	}

	@ResponseBody
	@RequestMapping(value = "/getImgByTitleId/{itemId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageByTitleId(@PathVariable long itemId,@RequestParam int width,@RequestParam int height){
	
		Item item=productService.getItemById(itemId);
		 
		return scale(item.getImage(), width, height);
			 
	}
	
	@RequestMapping(value="/removeFromcart",method=RequestMethod.POST)
	@CrossOrigin
	public void removeFromcart(Pageable pageable,@RequestBody Map<String, Object> reqMap) {

		long customerId=reqMap.get("customerId")!=null?Long.valueOf((String)reqMap.get("customerId")):0;
		long cartItemId=reqMap.get("cartItemId")!=null?Long.valueOf((Integer)reqMap.get("cartItemId")):0;
		
		Customer customer=customerService.getCustomerById(customerId);
		
		CartItem cartItem=cartServive.getCartItem(cartItemId);
		
		
		customer.getCart().setGrandTotal(customer.getCart().getGrandTotal()-cartItem.getTotalPriceDouble());
		
		customerService.save(customer);
		
		cartServive.deleteCartItem(cartItemId);
		
	}
	
	
/*	public byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth(); 
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }*/
	
	
	/**
	 * @param fileData		byte array of image.				
	 * @param width			The width of the image.
	 * @param height		The height of the image.
	 * @return				Byte stream of the resized image.
	 */
	public byte[] scale(byte[] fileData, int width, int height) {
		
		try {
			
			ByteArrayInputStream in = new ByteArrayInputStream(fileData);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			Thumbnails.of(in).size(width,height).crop(Positions.CENTER).toOutputStream(buffer);
			
			return buffer.toByteArray();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		    throw new RuntimeException(e.getMessage());
		    
		}
		
		
	}
	
	
	@RequestMapping(value="/purchaseItems",method=RequestMethod.POST)
	@CrossOrigin
	public void purchaseItems(@RequestBody Map<String, Object> reqMap){
			
		long customerId=reqMap.get("customerId")!=null?Long.valueOf((String)reqMap.get("customerId")):0;
		
		double totDeduction=0;
		
		Customer customer=customerService.getCustomerById(customerId);
		
		Cart cart=customer.getCart();
		
		List<CartItem> cartItems=cart.getCartItems();
		
		for(int i = 0; i<cartItems.size(); i++){
			
			CartItem cartItem=cartItems.get(i);
			
			Item item=cartItem.getItem();
			
			int quantity=item.getQuantity();
			
			quantity-=cartItem.getQuantity();
			
			if(quantity<1){
				quantity=0;
				item.setStatus("inactive");
			}
			
			item.setQuantity(quantity);
			
			
			PurchasedItems pur=new PurchasedItems();
			
			pur.setCustomer(customer);
			pur.setItem(item);
			pur.setNoOfItems(cartItem.getQuantity());
			pur.setPurchasePrice(cartItem.getTotalPriceDouble());
			pur.setPurchasedDate(new Date());
			
			productService.savePurchasedItems(pur);
			
			productService.saveItem(item);
			
			cartServive.deleteCartItem(cartItem.getId());

			totDeduction+=cartItem.getTotalPriceDouble();
			
			cart.getCartItems().remove(i);
			i-=1;
		}
		
		cart.setGrandTotal(cart.getGrandTotal()-totDeduction);
		
		customer.setCart(cart);
		
		customerService.save(customer);
		
	}
	
	@RequestMapping(value="/saveStockItem",method=RequestMethod.PUT)
	@CrossOrigin
	public void saveStockItem(@RequestBody StockItems stockItem) {

		if(stockItem.getNoOfItems()<1 || stockItem.getItem().getId()<1)
			return;
		
		Item item=productService.getItemById(stockItem.getItem().getId());
		item.setStatus("active");
		item.setQuantity(item.getQuantity()+stockItem.getNoOfItems());		
		item.setLupDate(new Date());
		
		stockItem.setItem(item);
		stockItem.setStatus("active");
		stockItem.setInventryDate(new Date());
		stockItem.setSellingPrice(item.getPrice());
		
		productService.saveStockItem(stockItem);
		
	}
	
	@RequestMapping(value="/getAllStockItems",method=RequestMethod.POST)
	@CrossOrigin
	public Page<StockItems> getAllStockItems(Pageable pageable,@RequestBody Map<String, Object> filterMap) {

		return productService.getAllStockItems(pageable,filterMap);

	}
	
//	@RequestMapping(value="/saveReview",method=RequestMethod.PUT)
//	@CrossOrigin
//	public ProductComment saveReview(ProductComment review) {
//
//		productService.saveReview(review);
//		
//		return review;
//		
//	}
	
}
