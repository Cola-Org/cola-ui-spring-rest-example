package com.colaui.example.service;

import com.colaui.example.model.DictionaryCode;
import com.colaui.provider.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface DictionaryCodeService {
    DictionaryCode find(int id);

    void save(DictionaryCode dictionaryCode);

    void update(DictionaryCode dictionaryCode);

    void delete(int id);

    List<DictionaryCode> find(int from, int limit);

    Page<DictionaryCode> getPage(int pageSize, int pageNo, String contain);

    List<DictionaryCode> getDictByCodes(Collection<Integer> codes);
}
