package ru.geekbrains.controller.repr;

import java.math.BigDecimal;
import java.util.List;

public class ProductRepr {

    private final Long id;

    private final String name;

    private final BigDecimal price;

    private final Long pictureId;

    private final List<Long> pictureIds;

    public ProductRepr(Long id, String name, BigDecimal price, Long pictureId, List<Long> pictureIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureId = pictureId;
        this.pictureIds = pictureIds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }
}
