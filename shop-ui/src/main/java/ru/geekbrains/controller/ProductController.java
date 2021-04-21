package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    public ProductController(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @GetMapping
    public String productListPage(@RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size,
            @RequestParam(name = "sortField") Optional<String> sortField,
            @RequestParam(name = "sortOrder") Optional<String> sortOrder,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
                                  Model model) {
        logger.info("Product list page");

        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("products", productService.findAll(page,size,sortField,sortOrder));
        model.addAttribute("products", productService.findByFilter(categoryId));

        return "categories-left-sidebar";
    }

    @GetMapping("/product/{id}")
    public String productPage(@PathVariable("id") Long id, Model model) {
        logger.info("Product page {}", id);

        model.addAttribute("product", productService.findById(id).orElseThrow(NotFoundException::new));

        return "product-details";
    }
}
