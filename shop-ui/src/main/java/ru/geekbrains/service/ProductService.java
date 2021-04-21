package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public Page<ProductRepr> findWithFilter(Integer page, Integer size,
//                                 String sortField) {
////        Specification<Product> spec = ProductSpecification.fetchPictures();
//        Specification<Product> spec = Specification.where(null);
//        if (sortField != null && !sortField.isBlank()) {
//            return productRepository.findAll(spec, PageRequest.of(page, size,
//                    Sort.by(sortField)))
//                    .map(ProductRepr::new);
//        }
//        return productRepository.findAll(spec, PageRequest.of(page, size))
//                .map(ProductRepr::new);


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
