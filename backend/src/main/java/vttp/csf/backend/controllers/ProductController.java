package vttp.csf.backend.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import vttp.csf.backend.models.Product;

@RestController
@RequestMapping("/api")
public class ProductController {

    private static final String BASE_URL = "https://fakestoreapi.com";

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String category) {
        String apiUrl = BASE_URL + "/products";
        if (category != null) {
            apiUrl += "?category=" + category;
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> response = restTemplate.getForEntity(apiUrl, Product[].class);
        List<Product> products = Arrays.asList(response.getBody());
        return ResponseEntity.ok(products);
    }

    // Retrieve details for a specific product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> response = restTemplate.getForEntity(BASE_URL + "/products/" + id, Product.class);
        Product product = response.getBody();
        return ResponseEntity.ok(product);
    }

    // Retrieve all categories
    @GetMapping("/categories")
    public List<String> getCategories() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> response = restTemplate.getForEntity(BASE_URL + "/products/categories", String[].class);
        return Arrays.asList(response.getBody());
    }
}
