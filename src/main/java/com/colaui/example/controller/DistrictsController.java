package com.colaui.example.controller;

import com.colaui.example.model.Districts;
import com.colaui.example.service.DistrictsService;
import com.colaui.provider.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictsController {
    @Autowired
    private DistrictsService districtsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Districts> getAll() {
        return districtsService.getAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Page<Districts> paging(@RequestParam int pageSize,
                                  @RequestParam int pageNo, @RequestParam(required = false) String contain) {
        return districtsService.getPage(pageSize, pageNo, contain);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        districtsService.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public void save(@RequestBody Districts districts) {
        districtsService.save(districts);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public void update(@RequestBody Districts districts) {
        districtsService.update(districts);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Districts find(@PathVariable("id") int id) {
        return districtsService.find(id);
    }

    @RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
    public List<Districts> find(@PathVariable("from") int from,
                                @PathVariable("limit") int limit) {
        return districtsService.find(from, limit);
    }

    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    public List<Districts> getProvinces() {
        return districtsService.getProvinces();
    }

    @RequestMapping(value = "/children", method = RequestMethod.GET)
    public List<Districts> getChildrenById(@RequestParam int parentId) {
        return districtsService.getChildrenById(parentId);
    }

    @RequestMapping(value = "/{id}/children", method = RequestMethod.GET)
    public List<Districts> getChildrenFromParent(@PathVariable int id) {
        return districtsService.getChildrenById(id);
    }
}
