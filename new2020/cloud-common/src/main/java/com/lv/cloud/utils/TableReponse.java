package com.lv.cloud.utils;

import java.util.List;

/**
 * 表格返回的封装类
* @ProjectName: emos
* @ClassName: TableReponse  
* @Description: TODO(用一句话描述该文件做什么)  
* @author duanxiang  
* @date 2017年9月29日  
* @Copyright: ©2015-2017 RuanYun Co.Ltd. All rights reserved.
* @param <T>
 */
public class TableReponse<T> {
	
	private String msg;
	
	private Integer code;
	
	private Integer count;
	
	private List<T> data;
	
	private double total;
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	

}
