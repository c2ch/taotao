package com.c2c.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @ClassName: SolrjTest
 * @Description: solrj的测试
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 11:28
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class SolrjTest {

    /**
     * 测试插入
     * @throws Exception
     */
    @Test
    public void testSolrJ() throws Exception{
        //1、创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.128:8080/solr");
        //2、创建一个文档输入对象
        SolrInputDocument document = new SolrInputDocument();
        //3、添加域
        document.addField("id", "solrtest01");
        document.addField("item_title", "测试SolrJ");
        document.addField("item_desc", "SolrJ真的好复杂的");
        //添加到索引库中
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    /**
     * 测试查询
     */
    @Test
    public void testQuery() throws SolrServerException {
        //1、创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.128:8080/solr");
        //2、创建查询对象
        SolrQuery query = new SolrQuery();
        query.setQuery("硬盘");//设置查询条件
        query.set("df", "item_title");
        query.setStart(0);
        query.setRows(100);
        //3、执行查询
        QueryResponse response = solrServer.query(query);
        //4、取查询结果
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("item_title"));
            System.out.println(result.get("item_desc"));
        }
    }
}
