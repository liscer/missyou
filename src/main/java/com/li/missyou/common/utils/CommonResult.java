package com.li.missyou.common.utils;

import com.li.missyou.core.configuration.ExceptionCodeConfiguration;
import com.li.missyou.exception.http.HttpException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Setter
@Getter
@Component
public class CommonResult<T> {
    @Autowired
    private  ExceptionCodeConfiguration exCodeConfiguration;
    private static CommonResult commonResult;
    private int code;
    private String message;
    private T data;

    @PostConstruct
    public void init() {
        commonResult=this;
        exCodeConfiguration = this.exCodeConfiguration;
    }

    public CommonResult() {
    }

    public CommonResult(int  code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseEntity<?> success(T data) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    CommonResult<T> result = new CommonResult<>(0, commonResult.exCodeConfiguration.getMessage(0), data);
    return new ResponseEntity<CommonResult<? extends T>>(result,headers, HttpStatus.OK);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponseEntity<?> success(T data, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CommonResult<T> result = new CommonResult<>(0, message, data);
        return new ResponseEntity<CommonResult<? extends T>>(result,headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<?> failed(T data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CommonResult<T> result = new CommonResult<>(0, "ok", data);
        return new ResponseEntity<CommonResult<? extends T>>(result,headers, HttpStatus.FORBIDDEN);
    }


    public static  ResponseEntity failed(int code, String message, HttpServletRequest req,HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        CommonResult result = new CommonResult(code, message,method + " " + requestUrl );
        return new ResponseEntity(result,headers, status);
    }

    public static ResponseEntity failed(HttpServletRequest req, HttpException e) {
        HttpStatus status = HttpStatus.resolve(e.httpStatusCode);
        return CommonResult.failed(e.code, commonResult.exCodeConfiguration.getMessage(e.code), req,status);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
//    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
//        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
//    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
//    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message) {
//        return new CommonResult<T>(errorCode.getCode(), message, null);
//    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
//    public static <T> CommonResult<T> failed(String message) {
//        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
//    }

    /**
     * 失败返回结果
     */
//    public static <T> CommonResult<T> failed() {
//        return failed(ResultCode.FAILED);
//    }

    /**
     * 参数验证失败返回结果
     */
//    public static <T> CommonResult<T> validateFailed() {
//        return failed(ResultCode.VALIDATE_FAILED);
//    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
//    public static <T> CommonResult<T> validateFailed(String message) {
//        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
//    }

    /**
     * 未登录返回结果10006登陆失败
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(10006,commonResult.exCodeConfiguration.getMessage(10006),data);
    }

    /**
     * 未授权返回结果10004令牌不合法或者过期
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(10004,commonResult.exCodeConfiguration.getMessage(10004), data);
    }
}
