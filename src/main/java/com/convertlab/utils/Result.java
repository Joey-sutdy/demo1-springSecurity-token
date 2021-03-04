package com.convertlab.utils;

import lombok.Data;

/**
 * created by limei on 2021/3/2
 * 说明:
 */
@Data
public class Result {

    private Integer status;
    private Object data;
    private String message;

    public Result(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, String message,Object data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }



    /**
     * 返回成功
     * @return
     */
    public static Result returnSuccess(){
        return new Result(CommonConstant.SUCCESS,CommonConstant.SUCCESS_MESSAGE);
    }
    /**
     * 返回失败
     * @return
     */
    public static Result returnFail(){
        return new Result(CommonConstant.FAIL,CommonConstant.FAIL_MESSAGE);
    }
    /**
     * 返回成功
     * @return
     */
    public static Result returnSuccess(String message){
        return new Result(CommonConstant.SUCCESS,message);
    }
    /**
     * 返回失败
     * @return
     */
    public static Result returnFail(String message){
        return new Result(CommonConstant.FAIL,message);
    }
    /**
     * 返回成功 自定义message
     * @param data
     * @param message
     * @return
     */
    public static Result returnSuccess(Object data,String message){
        return new Result(CommonConstant.SUCCESS,message,data);
    }
    /**
     * 返回成功
     * @param data
     * @return
     */
    public static Result returnSuccess(Object data){
        return new Result(CommonConstant.SUCCESS,CommonConstant.SUCCESS_MESSAGE,data);
    }
    public static Result returnSuccess(Integer status,String message,Object data){
        return new Result(status,message,data);
    }
}
