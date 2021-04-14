package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addProductQty(ProductRepr productRepr, String color, String material, int qty);

    void removeProductQty(ProductRepr productRepr, String color, String material, int qty);

    List<LineItem> getLineItems();

    BigDecimal getTotalPrice();

}
