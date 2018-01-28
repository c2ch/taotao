package com.c2c.search.service.impl;

import com.c2c.result.TaotaoResult;
import com.c2c.search.dao.SearchDao;
import com.c2c.search.mapper.SearchItemMapper;
import com.c2c.search.pojo.SearchItem;
import com.c2c.search.pojo.SearchResult;
import com.c2c.search.service.SearchItemSerivce;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SearchItemSerivceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 14:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class SearchItemSerivceImpl implements SearchItemSerivce {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchDao searchDao;

    /**
     * 导入索引库
     * @return
     */
    @Override
    public TaotaoResult importItems() throws Exception {
        List<SearchItem> list = searchItemMapper.getItemList();
        for (SearchItem searchItem : list) {
          //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSellPoint());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategoryName());
            document.addField("item_desc",searchItem.getItemDesc());
            //写入索引库
            solrServer.add(document);
        }

        //提交
        solrServer.commit();
        return TaotaoResult.ok();
    }

    /**
     * 查询
     * @param queryString
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @Override
    public TaotaoResult search(String queryString,int page,int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(queryString);
        //设置分页条件
        query.setStart(page-1);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "item_title");
        //设置高亮
        query.setHighlight(true);
        //设置高亮显示的域
        query.addHighlightField("item_title");
        //设置前后缀
        query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        query.setHighlightSimplePost("</font>");
        //执行查询
        SearchResult searchResult = searchDao.search(query);
        //设置总页数
        int pageCount = (int) (searchResult.getRecordCount() / rows);
        searchResult.setPageCount((searchResult.getRecordCount()) % rows == 0 ? pageCount : pageCount + 1);
        //设置当前页
        searchResult.setCurentPage(page);

        return TaotaoResult.ok(searchResult);
    }


    /**
     * 后台修改商品信息后同步索引库服务
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public TaotaoResult syncIndex(SearchItem item) throws Exception {
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //修改或者增加索引，如果id在索引库中存在则修改
        document.addField("id", item.getId());
        document.addField("item_title",item.getTitle());
        document.addField("item_sell_point",item.getSellPoint());
        document.addField("item_price",item.getPrice());
        document.addField("item_image",item.getImage());
        document.addField("item_category_name",item.getCategoryName());
        document.addField("item_desc",item.getItemDesc());
        //添加到文档
        solrServer.add(document);
        //提交
        solrServer.commit();

        return TaotaoResult.ok();
    }

    /**
     * 后台删除商品信息后删除索引服务
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TaotaoResult deleteIndex(String id) throws Exception {
        //根据Id删除索引
        solrServer.deleteById(id);
        solrServer.commit();
        return TaotaoResult.ok();
    }
}
