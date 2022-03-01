package com.dafiti.challenge.controller;

import com.dafiti.challenge.repository.CategoryRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.dafiti.challenge.model.Category;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value={"/",""})
    @Cacheable(value = "listCategory")
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable(value="id",required = true) Long id){
        return (categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @PostMapping(value={"/",""})
    @CacheEvict(value="listCategory", allEntries = true)
    @CrossOrigin(origins = "*")
    public Category setCategory(@RequestBody @Valid Category category){
        return (categoryRepository.save(category));
    }
    
    @PutMapping("/{id}")
    @CacheEvict(value="listCategory", allEntries = true)
    @CrossOrigin(origins = "*")
    public Category updateCategory(@PathVariable(value="id",required = true) Long id, @RequestBody @Valid Category category){

        Category category_new = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        category_new.setNome(category.getNome());

        return categoryRepository.save(category_new);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value="listCategory", allEntries = true)
    @CrossOrigin(origins = "*")
    public Category deleteCategory(@PathVariable(value="id",required = true) Long id){
        Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        categoryRepository.deleteById(id);
        return category;
    }


}
