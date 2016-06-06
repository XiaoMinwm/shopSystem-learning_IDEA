package com.taotao.rest;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by XiaoMin on 2016/6/6.
 */
public class SolrTest {
    @Test
    public void addDocument() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.121:8080/solr");
        SolrInputDocument document = new SolrInputDocument();

        document.addField("id", "test002");
        document.addField("item_title", "测试商品3");
        document.addField("item_price", 4335);

        solrServer.add(document);
        solrServer.commit();

    }
    @Test
    public void deleteDocument() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.121:8080/solr");
        /*solrServer.deleteById("test001");*/
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

}
