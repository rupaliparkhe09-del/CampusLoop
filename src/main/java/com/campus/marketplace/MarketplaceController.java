package com.campus.marketplace;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin
public class MarketplaceController {

    private final List<Map<String, Object>> products =
            new ArrayList<>();

    public MarketplaceController() {
        addDefault("Engineering Mathematics", 350, "Books");
        addDefault("Physics Notes", 120, "Notes");
        addDefault("Wireless Headphones", 650, "Electronics");
        addDefault("Scientific Calculator", 450, "Electronics");
    }

    private void addDefault(String name, int price, String category) {
        Map<String, Object> p = new LinkedHashMap<>();
        p.put("id", products.size() + 1);
        p.put("name", name);
        p.put("price", price);
        p.put("category", category);
        products.add(p);
    }

    @GetMapping("/api/products")
    public List<Map<String, Object>> getProducts() {
        return products;
    }

    @PostMapping("/api/products")
    public Map<String, Object> addProduct(
            @RequestBody Map<String, Object> product) {

        Map<String, Object> p = new LinkedHashMap<>();
        p.put("id", products.size() + 1);
        p.put("name", product.getOrDefault("name", "New Item"));
        p.put("price", product.getOrDefault("price", 0));
        p.put("category", product.getOrDefault("category", "Other"));

        products.add(p);
        return p;
    }

    @GetMapping("/api/status")
    public Map<String, String> status() {
        return Map.of(
            "app", "CampusLoop",
            "status", "running",
            "message", "Campus Marketplace API is ready"
        );
    }
}
