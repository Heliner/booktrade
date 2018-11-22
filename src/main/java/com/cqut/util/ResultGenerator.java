package com.cqut.util;

import com.cqut.result.AjaxResult;

import java.util.List;

public class ResultGenerator {
    private static final int OPERATION_SUCCESS = 1;
    private static final int OPERATION_FAILED = 0;
    private static final String FAILED_MESSAGE = "UNKNOWN_FAILED";
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    public static AjaxResult GENERATE_SUCCESS_RESULT() {
      /*  AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(null);
        ajaxResult.setResultCode(OPERATION_SUCCESS);
      */
        return new AjaxResult.AjaxResultBuilder().resultCode(OPERATION_SUCCESS).build();
    }


    //    TODO
    public static AjaxResult GENERATE_SUCCESS_RESULT(List<Object> objectList) {
/*
        AjaxResult<List<Object>> ajaxResult = new AjaxResult();
        ajaxResult.setData(object);
        ajaxResult.setResultCode(OPERATION_SUCCESS);
*/
        return new AjaxResult.AjaxResultBuilder().resultCode(OPERATION_SUCCESS).build();
    }

    public static AjaxResult GENERATE_SUCCESS_RESULT(Object object) {
     /*   AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setResultCode(OPERATION_SUCCESS);
        ajaxResult.setData(object);
     */
        return new AjaxResult.AjaxResultBuilder().resultCode(OPERATION_SUCCESS).data(object).build();
    }

    public static AjaxResult GENERATE_SUCCESS_RESULT(String message) {
        return new AjaxResult.AjaxResultBuilder().resultCode(OPERATION_SUCCESS).message(message).build();
    }

    public static AjaxResult GENERATE_SUCCESS_RESULT(String message, String url) {
        /*
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setMessage(message);
        ajaxResult.setResultCode(OPERATION_SUCCESS);
        ajaxResult.setUrl(url);
        */
        return new AjaxResult.AjaxResultBuilder().url(url).message(message).build();
    }

    public static AjaxResult GENERATE_SUCCESS_RESULT(Object object, String url, String message) {
      /*  AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(object);
        ajaxResult.setResultCode(OPERATION_SUCCESS);
        ajaxResult.setUrl(url);
        ajaxResult.setMessage(message);
*/
        return new AjaxResult.AjaxResultBuilder().data(object).url(url).message(message).build();
    }

    /**
     * 生成错误信息
     *
     * @param message
     * @return
     */
    public static AjaxResult GENERATE_FAILED_MESSAGE(String message) {
        return new AjaxResult.AjaxResultBuilder().message(message).build();
    }

    public static AjaxResult GENERATE_DEFAULT_FAILED_MESSAGE() {
        return new AjaxResult.AjaxResultBuilder().message(FAILED_MESSAGE).resultCode(OPERATION_SUCCESS).build();
    }

    public static AjaxResult GENERATE_SUCCESS_MESSAGE(String message) {
        return new AjaxResult.AjaxResultBuilder().message(message).build();
    }


}
