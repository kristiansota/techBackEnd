package com.kristian.test.mappers;

import com.kristian.test.model.Cart_items;
import dto.CartItemDTO;
import org.springframework.stereotype.Service;

@Service
public class MappersDTO {

    public CartItemDTO cartItemDTOMapper(Cart_items cart_items) {

        return new CartItemDTO(cart_items.getId(),
                cart_items.getProduct().getName(),
                cart_items.getProduct().getPrice(),
                cart_items.getQuantity(),
                cart_items.getProduct().getImagePath(),
                cart_items.getProduct().getId());
    }
}
