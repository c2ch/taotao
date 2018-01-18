package com.c2c.service.impl;

import com.c2c.mapper.TbItemParamMapper;
import com.c2c.pojo.TbItemParam;
import com.c2c.pojo.TbItemParamExample;
import com.c2c.pojo.TbItemParamExample.Criteria;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @ClassName: ItemParamServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/11 16:43
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {


    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 分页显示规格参数模板
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemParamList(Integer currentPage, Integer pageSize) {

        EasyUIDataGridResult result = new EasyUIDataGridResult();

        TbItemParamExample example = new TbItemParamExample();
        //设置分页条件
        PageHelper.startPage(currentPage,pageSize);
        //分页查询
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);

        //查询总计等详细信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(tbItemParams);

        result.setTotal(pageInfo.getTotal());
        result.setRows(tbItemParams);

        return result;
    }

    /**
     * 根据商品类目id查询规格参数模板是否存在
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult getItemParamByCid(long cid) {

        //根据商品类目Id查询商品规格参数模板是否有记录
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);

        //如果查询不到就不带数据返回
        if(tbItemParams.isEmpty()){
          return  TaotaoResult.ok();
        }
        //如果查询到数据就带数据返回
       return TaotaoResult.ok(tbItemParams.get(0));
    }


    /**
     * 根据商品类目id保存规格参数模板
     * @param cid
     * @param paramData 规格信息
     * @return
     */
    @Override
    public TaotaoResult saveItemParam(long cid, String paramData) {

        //保存商品规格模板,主键自增，不需要设置了。
        Date date = new Date();
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);

        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }

    /**
     * 根据规格参数模板ID删除规格参数模板
     * @param ids
     * @return
     */
    @Override
    public TaotaoResult deleteItemParamById(String ids) {
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(ids)){
            if(ids.indexOf(",")<0){
                criteria.andIdEqualTo(Long.parseLong(ids));
                tbItemParamMapper.deleteByExample(example);
            }else{
                //选择了不止一个id，需要切割遍历删除
                String [] id= ids.split(",");
                for(String i:id){
                    criteria.andIdEqualTo(Long.parseLong(i));
                    tbItemParamMapper.deleteByExample(example);
                }
            }
        }
        return TaotaoResult.ok();
    }
}
