package com.cosine.myweb.common;

import com.alibaba.fastjson2.JSON;

/**
 *@title ResModel
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description 响应结果模型
 */
public class ResModel<T> {
    private String statusCode;
    private String message;
    private T resultData;

    public String toJSONString(){
        return JSON.toJSONString(this);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResModel{" +
                "statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
