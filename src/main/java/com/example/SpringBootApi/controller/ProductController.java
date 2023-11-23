package com.example.SpringBootApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootApi.model.Product;
import com.example.SpringBootApi.service.ProductService;

@RestController
@RequestMapping("/prod/api")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;

	}

	@PostMapping("/add")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product newProduct = productService.save(product);
		return ResponseEntity.ok(newProduct);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Optional<Product>> fetchProductById(@PathVariable(value = "id") Long id) {
		Optional<Product> fetchProd = productService.getProductById(id);
		if (fetchProd.isPresent()) {
			return ResponseEntity.ok(fetchProd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProductById(@PathVariable(value = "productId") Long productId,
			@RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}
	@DeleteMapping("/remove/{delId}")
	public String deleteProductById(@PathVariable Long delId) {
		productService.deleteProduct(delId);
		return "Product deleted successfully against id :"+delId ;
	}

}
