package com.colaui.example.controller;

import com.colaui.example.model.DictionaryDetail;
import com.colaui.example.service.DictionaryDetailsService;
import com.colaui.provider.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dictionaryDetail")
public class DictionaryDetailsController {
    @Autowired
    private DictionaryDetailsService dictionaryDetailsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<DictionaryDetail> paging(@RequestParam int pageSize,
                                         @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        return dictionaryDetailsService.getPage(pageSize, pageNo, contain);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        dictionaryDetailsService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody DictionaryDetail dictionaryDetail) {
        dictionaryDetailsService.save(dictionaryDetail);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody DictionaryDetail dictionaryDetail) {
        dictionaryDetailsService.update(dictionaryDetail);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public DictionaryDetail find(@PathVariable("id") int id) {
        return dictionaryDetailsService.find(id);
    }

    @RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
    public List<DictionaryDetail> find(@PathVariable("from") int from,
                                       @PathVariable("limit") int limit) {
        return dictionaryDetailsService.find(from, limit);
    }
}
