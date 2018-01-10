package com.c2c.controller;

import com.c2c.result.EasyUITreeNodeResult;
import com.c2c.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: ItemCatController
 * @Description: 商品类目的controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/5 17:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNodeResult> getTreeNode(@RequestParam(value="id",defaultValue = "0") long parentId){
        List<EasyUITreeNodeResult> result = itemCatService.getTreeNode(parentId);
        return result;
    }
}
