package com.c2c.portal.service.impl;

import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;
import com.c2c.pojo.TbItemParamItem;
import com.c2c.portal.pojo.PortalItem;
import com.c2c.portal.service.ItemService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.HttpClientUtil;
import com.c2c.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ItemServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 15:34
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_BASE_URL}")
    private String ITEM_BASE_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;


    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(Long itemId) {
        //调用rest服务
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_URL + itemId);
        //转换成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json, PortalItem.class);
        if(result.getStatus()==200){
            TbItem item = (TbItem)result.getData();
            return item;
        }
        return null;
    }

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        //调用rest服务
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
        //转换成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        if(result.getStatus()==200){
            TbItemDesc itemDesc = (TbItemDesc)result.getData();
            return itemDesc;
        }
        return null;
    }

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    @Override
    public String getItemParamById(Long itemId) {
        //调用rest服务
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
        //转换成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        if(result.getStatus()==200){
            TbItemParamItem paramItem = (TbItemParamItem)result.getData();
            String paramJson = paramItem.getParamData();
            // 把规格参数的json数据转换成java对象
            // 转换成java对象
            List<Map> mapList = JsonUtils.jsonToList(paramJson, Map.class);
            // 遍历list生成html
            StringBuffer sb = new StringBuffer();

            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
            sb.append("	<tbody>\n");
            for (Map map : mapList) {
                sb.append("		<tr>\n");
                sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
                sb.append("		</tr>\n");
                // 取规格项
                List<Map> mapList2 = (List<Map>) map.get("params");
                for (Map map2 : mapList2) {
                    sb.append("		<tr>\n");
                    sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
                    sb.append("			<td>" + map2.get("v") + "</td>\n");
                    sb.append("		</tr>\n");
                }
            }
            sb.append("	</tbody>\n");
            sb.append("</table>");

            return sb.toString();
        }
        return null;
    }
}
