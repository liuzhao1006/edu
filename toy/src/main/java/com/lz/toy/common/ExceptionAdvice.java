package com.lz.toy.common;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;



@Log4j
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e,Model model) {
        log.error("参数验证失败,"+e.getMessage());
        model.addAttribute("error","参数验证失败,"+e.getMessage());
        return "error/500";
    }
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e,Model model) {
        log.error("参数解析失败,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,Model model) {
        log.error("不支持当前请求方法,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleHttpMediaTypeNotSupportedException(Exception e,Model model) {
        log.error("不支持当前媒体类型,"+e.getMessage());
        model.addAttribute("error","不支持当前媒体类型,"+e.getMessage());
        return "error/500";
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException  e,Model model) {
        log.error("资源不存在,"+e.getMessage());
        model.addAttribute("error","资源不存在,"+e.getMessage());
        return "error/500";

    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e,Model model) {
        System.out.println(e.toString());
        model.addAttribute("error","空指针异常,"+e.getMessage());
        return "error/500";
    }

    /**
     * 500
     * @param e 错误
     * @param model 实体
     * @return String 返回地址
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,Model model) {
        log.error("服务运行异常,"+e.getMessage());
        model.addAttribute("error","服务运行异常,"+e.getMessage());
        return "error/500";
    }
}
