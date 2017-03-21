package com.colaui.example.service;

import com.colaui.example.model.DictionaryDetail;
import com.colaui.provider.Page;

import java.util.List;

public interface DictionaryDetailsService {
    DictionaryDetail find(int id);

    void save(DictionaryDetail dictionaryDetail);

    void update(DictionaryDetail dictionaryDetail);

    void delete(int id);

    List<DictionaryDetail> find(int from, int limit);

    Page<DictionaryDetail> getPage(int pageSize, int pageNo, String contain);
}
