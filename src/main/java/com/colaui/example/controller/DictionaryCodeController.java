package com.colaui.example.controller;

import com.colaui.example.model.DictionaryCode;
import com.colaui.example.service.DictionaryCodeService;
import com.colaui.provider.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("dictionary")
public class DictionaryCodeController {
    @Autowired
    private DictionaryCodeService dictionaryCodeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<DictionaryCode> paging(@RequestParam int pageSize,
                                       @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        return dictionaryCodeService.getPage(pageSize, pageNo, contain);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        dictionaryCodeService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody DictionaryCode dictionaryCode) {
        dictionaryCodeService.save(dictionaryCode);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody DictionaryCode dictionaryCode) {
        dictionaryCodeService.update(dictionaryCode);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public DictionaryCode find(@PathVariable("id") int id) {
        return dictionaryCodeService.find(id);
    }

    @RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
    public List<DictionaryCode> find(@PathVariable("from") int from,
                                     @PathVariable("limit") int limit) {
        return dictionaryCodeService.find(from, limit);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<DictionaryCode> getDictByCodes(@RequestParam Collection<Integer> codes) {
        return dictionaryCodeService.getDictByCodes(codes);
    }
}
