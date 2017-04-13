/*
 * Copyright (C), 2013-2015, 猎上网
 * FileName: DataTableObj.java
 * Author:   ThinkPad
 * Date:     2015年7月7日 下午3:09:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.iamsj.utils.web;

/**
 * 前台datatable传参公共类<br>
 * 〈功能详细描述〉
 *
 * @author jay.shen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DataTableObj {
    /** 请求标识 */
    private int sEcho;
    /** 每页开始数标 */
    private int iDisplayStart;
    /** 每页显示条数 */
    private int iDisplayLength;
    /** 单排序列的下标 */
    private int iSortCol_0;
    /** 单排序列名 */
    private String sortCol;
    /** 单排序的方法 */
    private String sSortDir_0;
    /** 当前请求页 */
    private int currentPage;

    /**
     * @return sEcho 请求标示
     */
    public int getsEcho() {
        return sEcho;
    }

    /**
     * @param sEcho 请求标示
     */
    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    /**
     * @return iDisplayStart 每页开始数标
     */
    public int getiDisplayStart() {
        return iDisplayStart;
    }

    /**
     * @param iDisplayStart 每页开始数标
     */
    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    /**
     * @return iDisplayLength 每页显示条数
     */
    public int getiDisplayLength() {
        return iDisplayLength;
    }

    /**
     * @param iDisplayLength 每页显示条数
     */
    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    /**
     * @return iSortCol_0 单排序列的下标
     */
    public int getiSortCol_0() {
        return iSortCol_0;
    }

    /**
     * @param iSortCol_0 单排序列的下标
     */
    public void setiSortCol_0(int iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    /**
     * @return sSortDir_0 单排序的方法
     */
    public String getsSortDir_0() {
        return sSortDir_0;
    }

    /**
     * @param sSortDir_0 单排序的方法
     */
    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 得到当前的请求的页码
     * 
     * @return 返回页码数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getCurrentPage() {
        if (this.getiDisplayLength() >= 1 && this.getiDisplayStart() >= 0) {
            return (this.getiDisplayStart() / this.getiDisplayLength()) + 1;
        } else {
            currentPage = 1;
            return currentPage;
        }
    }

    /**
     * @param currentPage 当前页码
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return sortCol 单排序列名
     */
    public String getSortCol() {
        return sortCol;
    }

    /**
     * @param sortCol 单排序列名
     */
    public void setSortCol(String sortCol) {
        this.sortCol = sortCol;
    }
}
