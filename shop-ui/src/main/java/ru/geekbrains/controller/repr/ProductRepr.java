package ru.geekbrains.controller.repr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductRepr implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private Long pictureId;

    private List<Long> pictureIds;

    public ProductRepr() {
    }

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

    public void setId(Long id){ this.id = id;}

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public void setPictureIds(List<Long> pictureIds) {
        this.pictureIds = pictureIds;
    }
}
