package com.kidz.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kidz.cart.model.Customer;
import com.kidz.model.Cart;
import com.kidz.model.CartItem;
import com.kidz.model.Product;
import com.kidz.repository.CartItemRepository;
import com.kidz.repository.CartRepository;
import com.kidz.repository.CustomerRepository;
import com.kidz.repository.ProductRepository;

@Controller
@RequestMapping("/cart")
public class CartResources {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @RequestMapping(value = "/{cartId}",method = RequestMethod.GET)
    public  @ResponseBody Cart getCart(@PathVariable Long cartId) {
    	
    	return cartRepository.findOne(cartId);
    	
    }

    @RequestMapping(value = "saveItem/{productId}", method = RequestMethod.POST)
    public void saveItem(@RequestBody Map<String, Object> cartOptions,@PathVariable Long productId){

            int quantity = Integer.parseInt(String.valueOf(cartOptions.get("quantity")));
            String username= String.valueOf(cartOptions.get("username"));
            
        	Customer customer = customerRepository.findByEmail(username);
            Cart cart = customer.getCart();
            Product product = productRepository.findOne(productId);
            List<CartItem> cartItems = cart.getCartItems();
            
            for(int i = 0; i<cartItems.size(); i++){
            	
            //    if (product.getProductId()==cartItems.get(i).getProduct().getProductId()){
                  
                	CartItem cartItem = cartItems.get(i);
                    
             /*   	if(cartItem.getQuantity()+quantity <= product.getUnitInStock()) 
                        cartItem.setQuantity(cartItem.getQuantity() +quantity);
                    else {
              //          cartItem.setQuantity(product.getUnitInStock());
                        throw new IllegalArgumentException("Not so much quantity in sotck.");
                    }*/
                	
            //        cartItem.setTotalPriceDouble(product.getProductPrice()*cartItem.getQuantity());
                    
                    cartItemRepository.save(cartItem);

                    return;
              //  }
            }
            
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        //    cartItem.setTotalPriceDouble(product.getProductPrice()*cartItem.getQuantity());
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            
    	
    }

    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.DELETE)
    public void removeItem(@PathVariable Long cartItemId){

    	cartItemRepository.delete(cartItemId);
    
    }
    
    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.PUT)
    public void editItem(@RequestBody Map<String, Object> cartOptions,@PathVariable Long cartItemId){

    	int quantity = Integer.parseInt(String.valueOf(cartOptions.get("quantity")));
    	
    	CartItem cartItem = cartItemRepository.findOne(cartItemId);
   //     cartItem.setTotalPriceDouble(cartItem.getProduct().getProductPrice() * quantity);
        cartItem.setQuantity(quantity);
        
        cartItemRepository.save(cartItem);
        
    }

}
