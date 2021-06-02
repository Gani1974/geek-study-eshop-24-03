package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.service.CartService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    @MockBean
//    private CartService cartService;

//    @Autowired
//    private CartService cartService;

    private Product product;

    @BeforeEach
    private void init() throws Exception {
        Brand brand = brandRepository.save(new Brand("brand"));
        Category category = categoryRepository.save(new Category("category"));
        product = productRepository.save(new Product("Product", new BigDecimal(1234), category, brand));
    }

    @Test
            public void testAddToCart() throws Exception {

        MockHttpSession session = new MockHttpSession();

        mvc.perform(post("/cart/")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId",Long.toString(product.getId()))
                .param("qty","3")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cart"));

        CartService cartService = (CartService) session.getAttribute("scopedTarget.cartServiceImpl");
        assertFalse(cartService.getLineItems().isEmpty());
        assertEquals(1,cartService.getLineItems().size());
        assertEquals(3,cartService.getLineItems().get(0).getQty());
        assertTrue(product.getPrice().multiply(new BigDecimal(3)).compareTo(cartService.getSubTotal()) == 0);


//        Mockito.verify(cartService).addProductQty(
//                argThat(repr -> repr.getId().equals(product.getId()) &&
//                    repr.getName().equals(product.getName())),
//                any(),
//                any(),
//                eq(3));

    }


}
