package com.li.missyou.core;

import com.li.missyou.common.utils.CommonResult;
import com.li.missyou.exception.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        return CommonResult.failed(99999,e.getMessage(),req,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity handleHttpException(HttpServletRequest req, HttpException e) {
        e.printStackTrace();
        return CommonResult.failed(req,e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = formatAllErrorMessages(errors);
        return CommonResult.failed(10001,message,req,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintException(HttpServletRequest req, ConstraintViolationException e) {
        return CommonResult.failed(10001,e.getMessage(),req,HttpStatus.BAD_REQUEST);
    }

    public String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(';')
        );
        return errorMsg.toString();
    }
}
