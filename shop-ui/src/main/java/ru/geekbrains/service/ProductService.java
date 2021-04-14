package ru.geekbrains.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.ProductSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductService::mapToRepr);
    }

    public List<ProductRepr> findByFilter(Long categoryId) {
        Specification<Product> spec = ProductSpecification.fetchPictures();
        if (categoryId != null) {
            spec = spec.and(ProductSpecification.byCategory(categoryId));
        }
        return productRepository.findAll(spec).stream()
                .map(ProductService::mapToRepr)
                .collect(Collectors.toList());
    }

    private static ProductRepr mapToRepr(Product p) {
        return new ProductRepr(
                p.getId(),
                p.getName(), p.getPrice(),
                p.getPictures().size() > 0 ? p.getPictures().get(0).getId() : null,
                p.getPictures().stream().map(Picture::getId).collect(Collectors.toList())
        );
    }
}
