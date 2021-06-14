package com.yc.jpaplus.core.base.result;

import com.yc.jpaplus.core.base.utils.VoUtil;
import com.yc.jpaplus.core.base.vo.JpaPlusVo;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:15
*/
@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        return getResult(data, resultEnum.getCode(), resultEnum.getMessage());
    }

    public static Result success(String message) {
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        return getResult(null, resultEnum.getCode(), message);
    }

    public static Result error(ResultEnum resultEnum) {
        return getResult(resultEnum, null);
    }

    public static Result tokenError(String message) {
        ResultEnum resultEnum = ResultEnum.TOKEN_DEFECT_ERROR;
        return getResult(null, resultEnum.getCode(), message);
    }

    public static Result requestParamError(String message) {
        ResultEnum resultEnum = ResultEnum.PARAM_DEFECT_ERROR;
        return getResult(null, resultEnum.getCode(), message);
    }

    public static Result serverError(String message) {
        ResultEnum resultEnum = ResultEnum.SERVER_ERROR;
        return getResult(null, resultEnum.getCode(), message);
    }

    public static Result error(int code, String message) {
        return getResult(null, code, message);
    }

    public static Result getResult(Object data, int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result getResult(ResultEnum resultEnum, Object data) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(data);
        return result;
    }

    public <V extends JpaPlusVo> Result transVo(Class<V> clazz){
        VoUtil<V> lgmnVoUtil = VoUtil.getInstance();
        Object transResult;
        if(data instanceof List){
            transResult = lgmnVoUtil.transListToVoList((List)data,clazz);
        } else if(data instanceof Page){
            transResult = lgmnVoUtil.transPageToVoPage((Page)data,clazz);
        } else {
            transResult = lgmnVoUtil.transEntityToVo(data,clazz);
        }
        setData(transResult);
        return this;
    }

}
