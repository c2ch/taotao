package com.c2c.portal.service.impl;

import com.c2c.portal.pojo.SearchResult;
import com.c2c.portal.service.SearchService;
import com.c2c.result.TaotaoResult;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName: SearchServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 17:06
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) {

        //调用search服务
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            URIBuilder builder = new URIBuilder(SEARCH_BASE_URL);
            builder.addParameter("keyword", keyword);
            builder.addParameter("page", page+"");
            builder.addParameter("rows", rows+"");
            URI uri = builder.build();
            HttpGet get = new HttpGet(uri);
            response = httpClient.execute(get);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
        SearchResult searchResult = (SearchResult)taotaoResult.getData();
        return searchResult;
    }
}
