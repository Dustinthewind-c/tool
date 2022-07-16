package com.cy.tool.idempotent.constants;

import com.cy.tool.idempotent.domain.Result;

/**
 * @author ext.chenyi18
 * @ClassName ServiceCodeEnum
 * @Description: 档案服务的错误码
 * @date 2022/7/16 22:33
 */
@SuppressWarnings("ALL")
public enum ServiceCodeEnum {

    SYSTEM_ERROR(-1, "系统错误"),

    SUCCESS(1, "成功"),

    FAIL(2, "失败"),

    NOT_FREQUENTLY_OPERATE(1000,"请勿频繁操作!");

    /**
     * 错误码
     **/
    private Integer code;

    /**
     * 错误信息
     **/
    private String msg;

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

    /**
     * 构造方法
     **/
    ServiceCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result toFailedJsfResult() {
        return Result.getFailedResult(this.msg, this.code);

    }

    public Result toFailedJsfResult(String message) {
        return Result.getFailedResult(this.msg + ":" + message, this.code);
    }
}
