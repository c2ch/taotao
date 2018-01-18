package com.c2c.controller;

import com.c2c.result.EasyUITreeNodeResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ContentCategoryController
 * @Description: 内容分类管理Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 16:23
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNodeResult> getCategoryList(@RequestParam(value="id", defaultValue="0")long parentId) {
        List<EasyUITreeNodeResult> resultList = contentCategoryService.getContentCategoryList(parentId);
        return resultList;
    }

    /**
     * 新建分类
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(long parentId,String name){
        TaotaoResult result = contentCategoryService.createCategory(parentId, name);
        return result;
    }

    /**
     * 删除分类
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public void deleteCategory(long id) {
        contentCategoryService.deleteCategory(id);
    }

    /**
     * 重命名分类
     * @param id
     * @param name
     */
    @RequestMapping("/update")
    @ResponseBody
    public void renameCategory(long id, String name) {
        contentCategoryService.renameCategory(id,name);
    }
}
