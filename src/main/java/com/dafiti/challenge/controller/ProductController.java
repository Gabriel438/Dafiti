package com.dafiti.challenge.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.dafiti.challenge.model.Product;
import com.dafiti.challenge.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping(value={"/",""})
    public Page<Product> getProduct(@RequestParam int page,@RequestParam int size){
        Pageable paginate = PageRequest.of(page, size);
        return productRepository.findAll(paginate);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value="id") Long id){
        return (productRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    
    @PostMapping(value={"/",""})
    @CrossOrigin(origins = "*")
    public Product setProduct(@RequestBody @Valid Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Product updateProduct(@PathVariable(value="id",required = true) Long id,@RequestBody @Valid Product product){

        Product product_new = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        product_new.setNome(product.getNome());
        product_new.setPrice(product.getPrice());
        product_new.setSize(product.getSize());
        product_new.setCategory(product.getCategory());

        return productRepository.save(product_new);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Product updateProduct(@PathVariable(value="id",required = true) Long id){
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(id);
        return product;
    }


}
