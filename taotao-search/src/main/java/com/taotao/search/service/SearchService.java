package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by XiaoMin on 2016/6/7.
 */
public interface SearchService {
    SearchResult search(String query, int pages, int rows) throws Exception;
}
