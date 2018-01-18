package com.c2c.service;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;

/**
 *
 *
 * @ClassName: ItemParamService
 * @Description: 商品规格参数的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/11 16:42
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ItemParamService {


    /**
     * 分页显示规格参数模板
     * @param currentPage
     * @param pageSize
     * @return
     */
    EasyUIDataGridResult getItemParamList(Integer currentPage, Integer pageSize);


    /**
     * 根据商品类目id查询规格参数模板是否存在
     * @param cid
     * @return
     */
    TaotaoResult getItemParamByCid(long cid);

    /**
     * 根据商品类目id保存规格参数模板
     * @param cid
     * @param paramData 规格信息
     * @return
     */
    TaotaoResult saveItemParam(long cid,String paramData);

    /**
     * 根据规格参数模板ID删除规格参数模板
     * @param ids
     * @return
     */
    TaotaoResult deleteItemParamById(String ids);

}
