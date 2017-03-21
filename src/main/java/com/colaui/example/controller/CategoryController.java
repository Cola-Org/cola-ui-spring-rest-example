package com.colaui.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colaui.example.model.Category;
import com.colaui.example.service.CategoryService;
import com.colaui.provider.Page;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Category> paging(@RequestParam int pageSize,
                                 @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        return categoryService.getPage(pageSize, pageNo, contain);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        categoryService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Category category) {
        System.out.println(category.getCategoryName());
        categoryService.save(category);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Category category) {
        System.out.println(category.getCategoryName());
        categoryService.update(category);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Category find(@PathVariable("id") long id) {
        return categoryService.find(id);
    }

    @RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
    public List<Category> find(@PathVariable("from") int from,
                               @PathVariable("limit") int limit) {
        return categoryService.find(from, limit);
    }
}
