package com.c2c.result;

import java.util.List;

/**
 *  cc
 *
 * @ClassName: EasyUIDataGridResult
 * @Description: 按照EasyUI官方文档的要求，建立一个符合要求的返回格式类
 *
 *   Easyui中datagrid控件要求的数据格式为：
     {total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 14:54
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class EasyUIDataGridResult {

    private long total;
    private List<?> rows;

    public EasyUIDataGridResult() {
    }

    public EasyUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
