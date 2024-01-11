package com.zhang.roughshop.common.exception;

import com.zhang.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class RoughException extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public RoughException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
