package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created by XiaoMin on 2016/6/18.
 */
public interface SearchService {
    SearchResult search(String queryString, int page);
}
