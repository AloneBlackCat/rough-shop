package com.zhang.roughshop.common.exception;

import com.zhang.model.vo.common.Result;
import com.zhang.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error() {
        return Result.build(null, ResultCodeEnum.DATA_ERROR);
    }

    // 自定义异常处理
    @ExceptionHandler(RoughException.class)
    @ResponseBody
    public Result error(RoughException e) {
        return Result.build(null,e.getResultCodeEnum());
    }
}
