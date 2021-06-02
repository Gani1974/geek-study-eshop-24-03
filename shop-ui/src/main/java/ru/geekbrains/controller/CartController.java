package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.CartItemRepr;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

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
        return "shopping-cart";
    }

    @PostMapping
    public String addToCart(CartItemRepr cartItemRepr) {
        ProductRepr productRepr = productService.findById(cartItemRepr.getProductId())
                .orElseThrow(NotFoundException::new);
        cartService.addProductQty(productRepr, "", "", cartItemRepr.getQty());
        return "redirect:/cart";
    }

    @GetMapping("/add_product/{id}")
    public String addProductToCart(@PathVariable("id") Long productId){
        ProductRepr productRepr = productService.findById(productId)
                .orElseThrow(NotFoundException::new);
        cartService.addProductQty(productRepr,"","",1);
        return "redirect:/cart";
    }

//    @DeleteMapping("/product/{qty}/delete")
//    public String removeFromCart(@RequestParam(value = "qty") int qty,
//                                 @RequestParam(value = "productId") Long productId){
//        ProductRepr productRepr = productService.findById(productId)
//                .orElseThrow(NotFoundException::new);
//        cartService.removeProductQty(productRepr, "", "", qty);
//        return "redirect:/cart";
//    }

    @DeleteMapping
    public String delete(@RequestParam("productId") Long productId){
        cartService.removeProduct(new ProductRepr(productId,null,null,
        null,null),"","");
        return "redirect:/cart";
    }

    @PostMapping("/update_all_qty")
    public String updateAllQty(@RequestParam Map<Long, Integer> paramMap) {
        logger.info("Product Qty Map: {}", paramMap);
        cartService.updateAllQty(paramMap);
        return "redirect:/cart";
    }
}
