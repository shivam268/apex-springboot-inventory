package com.apex.eqp.inventory.services;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.helpers.ProductFilter;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final InventoryRepository inventoryRepository;
    private final RecalledProductRepository recalledProductRepository;
    private final RecalledProductService recalledProductService;

    @Transactional
    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProduct() {
        ProductFilter filter = new ProductFilter(recalledProductService.getAllRecalledProducts().stream().map(RecalledProduct::getName).collect(Collectors.toSet()));

        return filter.removeRecalledFrom(inventoryRepository.findAll());
    }

    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
