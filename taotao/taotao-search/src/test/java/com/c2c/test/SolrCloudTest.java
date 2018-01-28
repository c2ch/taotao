package com.c2c.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName: SolrCloudTest
 * @Description: SolrJ连接集群的测试
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/25 14:39
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class SolrCloudTest {

    /**
     * 测试连接solr集群
     */
    @Test
    public void testSolrJCloud() throws IOException, SolrServerException {

        //创建一个SolrServer对象连接Zookeeper，solr集群已有zookeeper管理
        CloudSolrServer solrServer = new CloudSolrServer("192.168.56.128:2181,192.168.56.128:2182,192.168.56.128:2183");
        //设置默认的SolrHome
        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "test01");
        solrInputDocument.addField("item_title", "测试solrJ连接集群");
        //添加文档
        solrServer.add(solrInputDocument);
        //提交
        solrServer.commit();

    }

    /**
     * 测试查询
     */
    @Test
    public void testQuery() throws SolrServerException {
        //创建一个SolrServer对象连接Zookeeper，solr集群已有zookeeper管理
        CloudSolrServer solrServer = new CloudSolrServer("192.168.56.128:2181,192.168.56.128:2182,192.168.56.128:2183");
        //设置默认的SolrHome
        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrQuery query = new SolrQuery();
        query.setQuery("solrJ");
        query.set("df", "item_title");
        QueryResponse response = solrServer.query(query);
        SolrDocumentList list = response.getResults();
        for (SolrDocument document : list) {
            System.out.println(document.get("item_title"));

        }

    }
}
