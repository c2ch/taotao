package com.c2c.search.dao;

import com.c2c.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @ClassName: SearchDao
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 15:32
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
