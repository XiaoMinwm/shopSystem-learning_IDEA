package com.taotao.search.dao.impl;


import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by XiaoMin on 2016/6/7.
 */
@Repository
public class SearchDaoImpl implements SearchDao {
    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        SearchResult result = new SearchResult();
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();

        result.setRecordCount(solrDocumentList.getNumFound());
        List<Item> list = new ArrayList<>();
        Map<String,Map<String,List<String>>> highlighting = queryResponse.getHighlighting();

        for (SolrDocument solrDocument : solrDocumentList) {
            //创建一商品对象
            Item item = new Item();
            item.setId((String) solrDocument.get("id"));
            //取高亮显示的结果
            List<String> listh = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (listh != null && listh.size()>0) {
                title = listh.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            //添加的商品列表
            list.add(item);

        }
        result.setItemList(list);
        return result;
    }
}
