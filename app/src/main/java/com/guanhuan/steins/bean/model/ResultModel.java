package com.guanhuan.steins.bean.model;

import com.google.gson.annotations.Expose;
import com.guanhuan.steins.config.Constants;


/**
 * 自定义返回结果
 * Created by 74405 on 2017/11/16.
 */
public class ResultModel<T> {

    @Expose
    private int code;

    @Expose
    private String message;

    @Expose
    private T content;

    public ResultModel(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, T content){
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isError(){ return this.code != Constants.SUCCESS_CODE; }

    @Override
    public String toString() {
        return "返回码:" + this.code + "\r\n消息:" + this.getMessage() +
                "\r\n内容:" + this.getContent();
    }
}
