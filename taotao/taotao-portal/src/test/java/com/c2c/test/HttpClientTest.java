package com.c2c.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HttpClientTest
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/17 16:55
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class HttpClientTest {


    /**
     * 测试Get请求
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        //1、创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2、创建HttpGet对象
        HttpGet get = new HttpGet("http://localhost:8083/rest/content/89");

        //3、执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //4、接受返回结果,HttpEntity对象
        HttpEntity entity = response.getEntity();

        //5、取响应的内容
        String html = EntityUtils.toString(entity);
        System.out.println(html);

        //6、关闭response,httpclient
        response.close();
        httpClient.close();
    }

    @Test
    public void testPost() throws IOException{
        //1、创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2、创建HttpPost对象，指定一个URL
        HttpPost post = new HttpPost("http://localhost:8890/post.html");

        //3、模拟一个表单，使用List集合，List中每个元素是一个NameValuePair对象
        List<NameValuePair> formList  = new  ArrayList<>();
        formList.add(new BasicNameValuePair("name", "张三"));
        formList.add(new BasicNameValuePair("pass", "1243"));

        //4、把表单包装到Entity对象中，StringEntity
        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        post.setEntity(entity);

        //5、执行请求
        CloseableHttpResponse response = httpClient.execute(post);

        //6、接受返回结果
        HttpEntity httpEntity = response.getEntity();
        String html = EntityUtils.toString(httpEntity);
        System.out.println(html);

        //7、关闭response,httpclient
        response.close();
        httpClient.close();

    }

}
