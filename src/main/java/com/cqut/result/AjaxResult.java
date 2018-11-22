package com.cqut.result;

import com.cqut.exception.CodeMsg;

import java.io.Serializable;

public class AjaxResult<T> implements Serializable {
    private T data = null;
    private String url = null;
    private String message = null;
    private int resultCode;

    private AjaxResult() {

    }

    private AjaxResult(AjaxResultBuilder ajaxResultBuilder) {
        this.message = ajaxResultBuilder.message;
        this.url = ajaxResultBuilder.url;
        this.resultCode = ajaxResultBuilder.resultCode;
    }

    private AjaxResult(CodeMsg cm) {
        resultCode = cm.getCode();
        message = cm.getMsg();

    }

    public static AjaxResult<String> error(CodeMsg cm) {
        return new AjaxResult<String>(cm);
    }

    public static class AjaxResultBuilder {
        //        private T data = null;
        private String url = null;
        private String message = null;
        private int resultCode = 0;
        private Object data = null;

        public AjaxResultBuilder url(String val) {
            this.url = val;
            return this;
        }

        public AjaxResultBuilder message(String val) {
            this.message = val;
            return this;
        }

        public AjaxResultBuilder resultCode(int val) {
            this.resultCode = val;
            return this;
        }

        public AjaxResultBuilder data(Object val) {
            this.data = val;
            return this;
        }

        public AjaxResult build() {
            return new AjaxResult(this);
        }

    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "AjaxResult{" + "url='" + url + '\'' +
                ", message='" + message + '\'' +
                ", resultCode='" + resultCode + '\'' +
                '}';
    }
}
