package com.colaui.example.service;

import com.colaui.example.model.Districts;
import com.colaui.provider.Page;

import java.util.List;

public interface DistrictsService {
    Districts find(int id);

    void save(Districts districts);

    void update(Districts districts);

    void delete(int id);

    List<Districts> find(int from, int limit);

    Page<Districts> getPage(int pageSize, int pageNo, String contain);

    List<Districts> getAll();

    List<Districts> getProvinces();

    List<Districts> getChildrenById(int parentId);

}
