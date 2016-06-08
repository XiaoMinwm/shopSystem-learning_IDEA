package com.taotao.search.service.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XiaoMin on 2016/6/7.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String queryString, int pages, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        query.setStart((pages-1) * rows);
        query.setRows(rows);
        query.set("df", "item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.search(query);
        Long recordCount = searchResult.getRecordCount();
        Long pageCount = recordCount / rows;
        if(recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);

        searchResult.setCurPage(pages);
        return searchResult;

    }

}
