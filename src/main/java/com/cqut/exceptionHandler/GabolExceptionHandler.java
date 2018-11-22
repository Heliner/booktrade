package com.cqut.exceptionHandler;

import com.cqut.exception.CodeMsg;
import com.cqut.exception.GlobalException;
import com.cqut.interceptor.ExceptionAspect;
import com.cqut.result.AjaxResult;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GabolExceptionHandler {

    private static final Logger log = Logger.getLogger(GabolExceptionHandler.class);

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    @ResponseStatus()
    public AjaxResult<String> GlobalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        GlobalException ex = (GlobalException) e;
        return AjaxResult.error(ex.getCm());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus()
    public AjaxResult<String> BindExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        BindException ex = (BindException) e;
        List<ObjectError> errors = ex.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return AjaxResult.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AjaxResult<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return AjaxResult.error(CodeMsg.SERVER_ERROR);
    }

}
