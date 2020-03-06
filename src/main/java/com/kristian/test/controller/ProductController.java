package com.kristian.test.controller;

import com.kristian.test.model.Cart_items;
import com.kristian.test.model.ProductModel;
import com.kristian.test.service.ProductsService;
import dto.CartItemDTO;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    private ProductsService productsService;

    //GET ALL PRODUCTS
    @GetMapping("/products")
    public List<ProductModel> getProducts() {
        return productsService.findAll();
    }

    //ADD NEW PRODUCT
    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public ProductModel addNewProduct(@RequestBody ProductDTO productDTO) {
        return productsService.addNewProduct(productDTO);
    }

    @RequestMapping(value = "/products/update/{productId}", method = RequestMethod.PUT)
    public ProductModel updateProduct(@PathVariable Long productId,
                                      @RequestBody ProductDTO productDTO ) {
        return productsService.updateProduct(productId, productDTO);
    }

    //DELETE A PRODUCT
    @RequestMapping(value = "/products/delete/{productId}", method = RequestMethod.DELETE)
    public ProductModel deleteProduct(@PathVariable Long productId) {
        return productsService.deleteProduct(productId);
    }


    //ADD ITEMS TO CART
    @PostMapping("/cart/add")
    public Cart_items addToCart(@RequestBody long productId){
        return productsService.addToCart(productId);
    }

    //UPDATE QUANTITY OF A PRODUCT ON SHOPPING CART
    @RequestMapping(value = "/cart/update_quantity/{cartItemId}", method = RequestMethod.PUT)
    public Cart_items updateQuantity(@PathVariable Long cartItemId,
                                      @RequestBody int newQuantity) {
        return productsService.updateQuantity(cartItemId,newQuantity);
    }

    //DELETE AN ITEM FROM THE CART
    @RequestMapping(value = "/cart/delete/{cartItemId}", method = RequestMethod.DELETE)
    public void deleteCartItem(@PathVariable Long cartItemId) {

        productsService.deleteCartItem(cartItemId);
    }

    //GET CART  ITEMS OF THE LOGGED ON USER
    @GetMapping("/cart")
    public List<CartItemDTO> getCartItems(){
        return productsService.getCurrentUserItems();
    }

    @GetMapping("/cart/existingCartItem/{productId}")
    public boolean existingCartItem (@PathVariable Long productId) {
        return productsService.existingCartItem(productId);
    }

    @GetMapping("/cart/numberOfItemsInCart")
    public int numberOfItemsInCart() {
        return productsService.getCurrentUserItems().size();
    }

    @RequestMapping(value = "/cart/buyItems", method = RequestMethod.DELETE)
    public void buyItems() {
        productsService.buyItemsInCart();
    }

}
