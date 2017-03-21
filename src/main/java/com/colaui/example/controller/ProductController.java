package com.colaui.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colaui.example.model.Product;
import com.colaui.example.service.ProductService;
import com.colaui.provider.Page;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Product> queryOfCategoryId(@RequestParam int pageSize,
                                           @RequestParam int pageNo, @RequestParam Long categoryId) {
        return productService.queryOfCategoryId(pageSize, pageNo, categoryId);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Product product) {
        productService.update(product);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Product find(@PathVariable("id") long id) {
        return productService.find(id);
    }

    @RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
    public List<Product> find(@PathVariable("from") int from,
                              @PathVariable("limit") int limit) {
        return productService.find(from, limit);
    }
}
