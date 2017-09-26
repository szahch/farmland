package com.szahch.dto;

/**
 * 返回封装数据
 * 
 * @author AlexZHOU 2017.9.23
 *
 * @param <T>
 *            泛型
 */
public class Result<T> {

	/**
	 * resultCode = 200 时查询成功，其他代码表示查询失败
	 */
	private int resultCode;

	/**
	 * true 表示查询成功并返回， false 表示查询失败
	 */
	private boolean success;

	/**
	 * 返回查询失败的信息， 查询成功时为空
	 */
	private String errorInfo;

	/**
	 * 返回对应的数据， 查询失败为空
	 */
	private T data;

	/**
	 * 查询结果分两种情况
	 *  1.查询成功但是数据出错 
	 *  2.查询失败
	 * 
	 * @param success
	 *            false
	 * @param resultCode
	 *            自定
	 * @param errorInfo
	 *            自定
	 */
	public Result(boolean success, int resultCode, String errorInfo) {
		this.resultCode = resultCode;
		this.success = success;
		this.errorInfo = errorInfo;
	}
	
	/**
	 * 查询成功，返回数据
	 * 
	 * @param success
	 *            true
	 * @param resultCode
	 *            200
	 * @param data
	 *            数据
	 */
	public Result(boolean success, int resultCode, T data) {
		this.success = success;
		this.resultCode = resultCode;
		this.data = data;
	}

	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the succeed
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param succeed
	 *            the succeed to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

}
