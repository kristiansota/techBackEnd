package com.kristian.test.service;

import com.kristian.test.mappers.MappersDTO;
import com.kristian.test.model.Cart_items;
import com.kristian.test.model.ProductModel;
import com.kristian.test.model.User;
import com.kristian.test.repository.CartItemsRepository;
import com.kristian.test.repository.ProductsRepository;
import dto.CartItemDTO;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MappersDTO mappersDTO;

    @Autowired
    private CartItemsRepository cartItemsRepository;


    //GET ALL PRODUCTS
    public List<ProductModel> findAll(){
        return productsRepository.findAllProducts();
    }



    //ADD NEW PRODUCT BY ADMIN
    public ProductModel addNewProduct(ProductDTO productDTO) {
        ProductModel product = new ProductModel();

        product.setName(productDTO.getName());
        product.setPrice((double) productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImagePath(productDTO.getImagePath());
        product.setQuantityAvailable(100);
        product.setToDate(null);

        return productsRepository.save(product);
    }


    //GET ALL CART ITEMES THAT THE LOGGED ON USER HAS IN HIS SHOPPING CART
    public List<CartItemDTO> getCurrentUserItems() {

        return cartItemsRepository.findItemsByUserId(myUserDetailsService.getCurrentUser().getUserId())
                .stream().map(cartItem -> mappersDTO.cartItemDTOMapper(cartItem)).collect(Collectors.toList());
    }


    //ADD AN ITEM TO THE SHOPPING CART OF LOGGED ON USER
    public Cart_items addToCart(long productId){

        User user = myUserDetailsService.getCurrentUser();
        ProductModel product = productsRepository.getOne(productId);
        Cart_items cart_item = new Cart_items();
        cart_item.setQuantity(1);
        cart_item.setUser(user);
        cart_item.setProduct(product);

        return cartItemsRepository.save(cart_item);

    }


    public ProductModel updateProduct(Long productId, ProductDTO productDTO) {
        ProductModel product = productsRepository.findById(productId).orElse(null);

        product.setName(productDTO.getName());
        product.setPrice((double)productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImagePath(product.getImagePath());

        return productsRepository.save(product);
    }

    public ProductModel deleteProduct(Long productId) {
        ProductModel product = productsRepository.findById(productId).orElse(null);

        product.setToDate(new Date());

        return productsRepository.save(product);
    }

    public Cart_items updateQuantity(Long cartItemId, int newQuantity) {
        Cart_items cartItem = cartItemsRepository.findById(cartItemId).orElse(null);

        cartItem.setQuantity(newQuantity);

        return cartItemsRepository.save(cartItem);
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {

        cartItemsRepository.deleteCartItem(cartItemId);
    }

    public boolean existingCartItem(Long productId) {

        long userId = myUserDetailsService.getCurrentUser().getUserId();

        Cart_items cart_items = cartItemsRepository.existingCartItem(productId, userId);

        if (cart_items != null) {
            return true;
        } else {
            return false;
        }
    }
}
