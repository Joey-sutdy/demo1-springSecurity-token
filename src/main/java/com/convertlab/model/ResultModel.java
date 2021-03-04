package com.convertlab.model;

/**
 * created by limei on 2021/3/2
 * 说明:
 */
public class ResultModel {

    private String errorCode;
    private String message;
    private Object data;

    public ResultModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResultModel(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultCode resultCodeEnum, Object data) {
        this.errorCode = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
        this.data = data;
    }

    public ResultModel(ResultCode resultCodeEnum) {
        this.errorCode = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
    }

    public String geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
