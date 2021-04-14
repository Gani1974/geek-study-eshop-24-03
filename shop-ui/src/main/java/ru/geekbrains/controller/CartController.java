package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.CartItemRepr;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    public final CartService cartService;

    public final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "shopping-cart";
    }

    @PostMapping
    public String addToCart(CartItemRepr cartItemRepr) {
        ProductRepr productRepr = productService.findById(cartItemRepr.getProductId())
                .orElseThrow(NotFoundException::new);
        cartService.addProductQty(productRepr, "", "", cartItemRepr.getQty());
        return "redirect:/cart";
    }

    @DeleteMapping("/product/{qty}/delete")
    public String removeFromCart(@RequestParam(value = "qty") int qty,
                                 @RequestParam(value = "productId") Long productId){
        ProductRepr productRepr = productService.findById(productId)
                .orElseThrow(NotFoundException::new);
        cartService.removeProductQty(productRepr, "", "", qty);
        return "redirect:/cart";
    }
}
