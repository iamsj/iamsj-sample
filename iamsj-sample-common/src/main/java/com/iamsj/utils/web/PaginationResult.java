/*
 * Copyright (C), 2002-2013, IBM
 * FileName: PaginationResult.java
 * Author:   12010065
 * Date:     2013-5-13 下午4:19:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.iamsj.utils.web;

import java.io.Serializable;

/**
 * 
 * 功能描述：封装返回信息
 * 
 * @author jay.shen
 * @version 1.0.0
 * @param <R>
 */
public class PaginationResult<R> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2361929152482573703L;

    /**
     * 结果集
     */
    private R r;

    /**
     * 分页信息
     */
    private Pagination pagination;

    /**
     * 构造方法
     * 
     * @param r 结果集
     * @param pagination 分页配置参数
     */
    public PaginationResult(R r, Pagination pagination) {
        super();
        this.r = r;
        this.pagination = pagination;
    }

    public PaginationResult() {
    	
    }

    /**
     * 返回结果集
     * 
     * @return 结果集
     */
    public R getR() {
        return r;
    }

    /**
     * 设置结果集
     * 
     * @param r 结果集
     */
    public void setR(R r) {
        this.r = r;
    }

    /**
     * 获取分页配置
     * 
     * @return 分页配置
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 设置分页配置
     * 
     * @param pagination 分页配置
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
