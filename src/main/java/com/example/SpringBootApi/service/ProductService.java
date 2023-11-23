package com.example.SpringBootApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.SpringBootApi.model.Product;
import com.example.SpringBootApi.repository.repo.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> getProductById(Long id) {

		return productRepository.findById(id);
	}

	public ResponseEntity<Product> updateProduct(Long productId, Product updateProd) {

		if (productId == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(productId)));
		existingProduct.setName(updateProd.getName());
		existingProduct.setPrice(updateProd.getPrice());
		existingProduct.setQuantity(updateProd.getQuantity());
		Product saveProd = productRepository.save(existingProduct);

		return ResponseEntity.ok(saveProd);
	}

	public ResponseEntity<String> deleteProduct(Long delId) {
		productRepository.deleteById(delId);
		return ResponseEntity.ok("Product deleted Successfully");
		
		
	}
}
