/*
 * Copyright (C), 2013-2015, 猎上网
 * FileName: AjaxDataTableObj.java
 * Author:   ThinkPad
 * Date:     2015年7月7日 下午3:12:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.iamsj.utils.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author jay.shen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AjaxDataTableObj<T> {
	/** 返回数据 */
	private List<T> aaData;
	/** 请求标识 */
	private int sEcho;
	/** 数据库中总共有多少条 */
	private int iTotalRecords = 0;
	/** 数据库中查询过滤后有多少条记录,和iTotalRecords设置为相同 */
	private int iTotalDisplayRecords = 0;

	/**
	 * 无参构造函数
	 */
	public AjaxDataTableObj() {
	}

	/**
	 * 构造函数
	 * 
	 * @param sEcho
	 *            请求标识
	 */
	public AjaxDataTableObj(int sEcho) {
		this.sEcho = sEcho;
		this.aaData = new ArrayList<T>();
	}

	/**
	 * 构造函数
	 * 
	 * @param sEcho
	 *            请求标识
	 * @param prs
	 *            分页结果集
	 */
	public AjaxDataTableObj(int sEcho, PaginationResult<List<T>> prs) {
		this.sEcho = sEcho;
		// 设置列表数据
		this.aaData = prs == null ? new ArrayList<T>() : prs.getR();
		if (prs != null) {

			// 设置显示总记录条数
			this.iTotalDisplayRecords = prs.getPagination().getTotalRows();
			// 设置总记录条数
			this.iTotalRecords = prs.getPagination().getTotalRows();
		}

	}

	/**
	 * @return aaData 列表数据
	 */
	public List<T> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData
	 *            列表数据
	 */
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	/**
	 * @return sEcho 请求标识
	 */
	public int getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho
	 *            请求标识
	 */
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return iTotalRecords 总记录条数
	 */
	public int getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords
	 *            总记录条数
	 */
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return iTotalDisplayRecords 总显示记录条数
	 */
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords
	 *            总显示记录条数
	 */
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
