package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by XiaoMin on 2016/6/7.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
