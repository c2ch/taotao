package com.c2c.service.impl;

import com.c2c.mapper.TbContentCategoryMapper;
import com.c2c.pojo.TbContentCategory;
import com.c2c.pojo.TbContentCategoryExample;
import com.c2c.result.EasyUITreeNodeResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContentCategoryServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 16:14
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    /**
     * 查询内容分类管理的列表展示（树形结构）
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeNodeResult> getContentCategoryList(long parentId) {

        //查询内容分类列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = categoryMapper.selectByExample(example);
        //返回EasyUI树形结构
        List<EasyUITreeNodeResult> resultList = new ArrayList<EasyUITreeNodeResult>();

        //遍历内容分类列表构建EasyUITreeNodeResult对象
        for (TbContentCategory category :tbContentCategories){
            EasyUITreeNodeResult node = new EasyUITreeNodeResult();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            //添加到列表
            resultList.add(node);
        }

        return resultList;
    }

    /**
     * 新建分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public TaotaoResult createCategory(long parentId, String name) {
        //构造TbContentCategory对象
        Date date = new Date();
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setIsParent(false);
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setUpdated(date);
        contentCategory.setCreated(date);
        //排序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
        contentCategory.setSortOrder(1);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        //插入数据
        categoryMapper.insert(contentCategory);
        //取得插入后的主键
        long id = contentCategory.getId();

        //根据parentId查询上级，判断IsParent属性，如果为false，设置为true
        TbContentCategory category = categoryMapper.selectByPrimaryKey(parentId);
        if(!category.getIsParent()){
            category.setIsParent(true);
            //根据主键更新
            categoryMapper.updateByPrimaryKey(category);
        }
        return TaotaoResult.ok(id);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(long id) {
        //1、删除的是叶子节点，删完该叶子节点还有其他叶子节点
        //2、删除的是叶子节点，删完该叶子节点没有其他叶子节点，父节点需要变为叶子节点
        //3、删除的是父节点，父节点的所有叶子节点也要被删除

        //根据id查询该节点是否是叶子节点
        TbContentCategory contentCategory = categoryMapper.selectByPrimaryKey(id);
        long parentId = contentCategory.getParentId();
        if(!contentCategory.getIsParent()){
            //该节点叶子节点

            //查询该节点对应的父节点的所有叶子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            List<TbContentCategory> tbContentCategories = categoryMapper.selectByExample(example);
            //如果只有一个叶子节点，删除之
            if(tbContentCategories.size()==1){
                //删除
                categoryMapper.deleteByPrimaryKey(id);
                //修改父节点状态
                contentCategory.setIsParent(false);
                categoryMapper.updateByPrimaryKey(contentCategory);
            }else{
                //不止一个叶子节点，删除之
                categoryMapper.deleteByPrimaryKey(id);
            }
        }else {
            //父节点
            //删除所有父节点是该节点的节点
            deleteCase(id);
            //删除自己
            categoryMapper.deleteByPrimaryKey(id);
        }

    }

    /**
     * 递归删除父节点的所有子节点，包括子节点也是父节点的内容分类
     */
    public void deleteCase(long id){
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        //根据当前id查询所有字节点
        List<TbContentCategory> list = categoryMapper.selectByExample(example);
        //遍历所有子节点
        for (TbContentCategory contentCategory : list) {
            //如果是叶子节点直接删除
            if(!contentCategory.getIsParent()){
                categoryMapper.deleteByPrimaryKey(contentCategory.getId());
            }else{
                //先删除自己
                categoryMapper.deleteByPrimaryKey(contentCategory.getId());
                //如果是父节点，递归查询出所有子节点并删除
                deleteCase(contentCategory.getId());
            }
        }

    }


    /**
     * 重命名分类
     * @param id
     * @param name
     */
    @Override
    public void renameCategory(long id, String name) {
        TbContentCategory contentCategory = categoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        categoryMapper.updateByPrimaryKey(contentCategory);

    }
}
