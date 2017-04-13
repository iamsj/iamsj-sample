package com.core.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 通用的分页类
 *
 * @author leo
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 9184749336548686198L;
    private List<T> list = Collections.emptyList();// 当页要显示的对象集
    private int pageId;// 当前页面ID，从1开始(首页pageId为1)，1,2,3,4,....
    private int pageSize = 1;// 每个页面的对象数
    private int maxPageId;// 末页ID
    private int totalCount;// 结果总数

    public PageResult(List<T> list, int pageId, int pageSize, int totalCount) {
        this.list = list;
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        int max = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            max = max + 1;
        } else {
            if (totalCount == 0)
                max = 1;
        }
        this.maxPageId = max;
    }

    public PageResult(List<T> list, int pageId, int pageSize, int maxPageId, int totalCount) {
        this.list = list;
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.maxPageId = maxPageId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getMaxPageId() {
        return maxPageId;
    }

    public void setMaxPageId(int maxPageId) {
        this.maxPageId = maxPageId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
