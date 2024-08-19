package org.example.thymeleaf_springboot.repository;

import org.example.thymeleaf_springboot.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
