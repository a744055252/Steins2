package com.guanhuan.steins.data.model;

import com.google.gson.annotations.Expose;


/**
 * 自定义返回结果
 * Created by 74405 on 2017/11/16.
 */
public class ResultModel<T>  {

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
}
