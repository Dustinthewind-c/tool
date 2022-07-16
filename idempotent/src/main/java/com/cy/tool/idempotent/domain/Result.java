package com.cy.tool.idempotent.domain;


import java.io.Serializable;

/**
 * @author ext.chenyi18
 * @ClassName Result
 * @Description 服务返回结构的封装
 * @date 2022/7/16 21:36
 */
public class Result<T> implements Serializable {


    private static final long serialVersionUID = -2643957452043024918L;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回结果信息
     */
    private String msg;
    /**
     * 返回结果数据
     */
    private T data;

    /**
     * 操作是否成功
     */
    private boolean success = false;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static <T> Result<T> getSuccessResult(T t, Integer code) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setData(t);
        r.setCode(code);
        return r;
    }

    public static <T> Result<T> getSuccessResultWithOutData(String message, Integer code) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setMsg(message);
        r.setCode(code);
        return r;
    }

    public static <T> Result<T> getFailedResult(String message, Integer code) {
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setMsg(message);
        r.setCode(code);
        return r;
    }

    public static <T> Result<T> getFailedResult(T data, String message, Integer code) {
        Result<T> r = new Result<>();
        r.setData(data);
        r.setSuccess(false);
        r.setMsg(message);
        r.setCode(code);
        return r;
    }

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(Integer code, T data) {
        this(code, data, null);
    }

    public Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
