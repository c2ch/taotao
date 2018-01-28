package com.c2c.service.impl;

import com.c2c.mapper.TbItemDescMapper;
import com.c2c.mapper.TbItemMapper;
import com.c2c.mapper.TbItemParamItemMapper;
import com.c2c.pojo.*;
import com.c2c.pojo.TbItemExample.Criteria;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ItemService;
import com.c2c.util.IDUtils;
import com.c2c.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  cc
 *
 * @ClassName: ItemServiceImpl
 * @Description: 商品服务接口的实现类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 10:22
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /**
     * 根据id获取商品信息
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(long itemId) {

//        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if(!list.isEmpty()){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 利用分页插件， 分页返回商品信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(Integer currentPage, Integer pageSize) {
        TbItemExample example = new TbItemExample();
        //设置分页
        PageHelper.startPage(currentPage,pageSize);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);

        //得到分页详细信息，如总数等等
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult result = new EasyUIDataGridResult(total,tbItems);

        return result;
    }

    /**
     * 添加商品信息
     * @param tbItem
     * @param desc 商品描述，不在TbItem类中，在TbItemDesc中
     * @param itemParams 商品规格参数
     * @return
     */
    @Override
    public TaotaoResult saveItem(TbItem tbItem, String desc,String itemParams) {

        TaotaoResult result = new TaotaoResult();

        //商品id
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除'
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        //创建时间
        tbItem.setCreated(date);
        //修改时间
        tbItem.setUpdated(date);
        //保存商品信息
        tbItemMapper.insert(tbItem);

        //保存商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(date);
        itemDesc.setCreated(date);
        tbItemDescMapper.insert(itemDesc);

        //保存规格参数
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setUpdated(date);
        itemParamItem.setCreated(date);
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        tbItemParamItemMapper.insert(itemParamItem);


        return TaotaoResult.ok();
    }


    /**
     * 根据商品ID展示规格参数信息
     * @param ItemId
     * @return
     */
    @Override
    public String getItemParamHtml(long ItemId) {

        //根据商品ID查询商品信息
        TbItemParamItemExample example = new TbItemParamItemExample ();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(ItemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list == null || list.isEmpty()) {
            return"";
        }
        //取规格参数
        TbItemParamItem itemParamItem = list.get(0);
        //取json数据
        String paramData = itemParamItem.getParamData();
        //转换成java对象
        List<Map>mapList = JsonUtils.jsonToList(paramData, Map.class);
        //遍历list生成html
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : mapList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("		</tr>\n");
            //取规格项
            List<Map>mapList2 = (List<Map>) map.get("params");
            for (Map map2 : mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("			<td>"+map2.get("v")+"</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();
    }

    /**
     * 根据商品Id删除商品信息
     * @param ids
     * @return
     */
    @Override
    public TaotaoResult deleteItemById(String ids) {
        //先判断Id是否是多个
        if(ids.indexOf(",")>0){
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                //先删除tbItem
                tbItemMapper.deleteByPrimaryKey(Long.parseLong(id[i]));
                //再删除tbItemDesc
                tbItemDescMapper.deleteByPrimaryKey(Long.parseLong(id[i]));
            }
        }else{
            //先删除tbItem
            tbItemMapper.deleteByPrimaryKey(Long.parseLong(ids));
            //再删除tbItemDesc
            tbItemDescMapper.deleteByPrimaryKey(Long.parseLong(ids));
        }

        return TaotaoResult.ok();
    }
}
