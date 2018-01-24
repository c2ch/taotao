package com.c2c.search.dao.impl;

import com.c2c.search.dao.SearchDao;
import com.c2c.search.pojo.SearchItem;
import com.c2c.search.pojo.SearchResult;
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
 * @ClassName: SearchDaoImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 15:33
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception{
        //根据查询条件，获取查询结果
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();
        List<SearchItem> searchItemList = new ArrayList<>();
        //遍历查询结果，构建SearchResult对象
        for (SolrDocument document : documentList) {
            //构建SearchItem对象
            SearchItem item = new SearchItem();
            item.setId((String)document.get("id"));
            item.setCategoryName((String) document.get("item_category_name"));
            item.setImage((String) document.get("item_image"));
            item.setSellPoint((String) document.get("item_sell_point"));
            item.setPrice((Long) document.get("price"));
            //1、取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //2、选择要设置高亮的域，这里根据id先获取文档对象，再获取域
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            //3、设置高亮
            String item_title = "";
            if(list.isEmpty()){
                item_title = (String) document.get("item_title");
            }else{
                //要高亮显示的内容
                item_title = list.get(0);
            }
            item.setTitle(item_title);
            //添加到列表
            searchItemList.add(item);
        }

        //设置result对象
        SearchResult result = new SearchResult();
        result.setItemList(searchItemList);
        result.setRecordCount(documentList.getNumFound());
        return result;
    }
}
