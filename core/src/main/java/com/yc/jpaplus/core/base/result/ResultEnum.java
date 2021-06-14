package com.yc.jpaplus.core.base.result;

/**
* @Author: TJM
* @Date: 2020/3/18 15:15
*/
public enum ResultEnum {
    SUCCESS("请求成功", 200),
    TOKEN_DEFECT_ERROR("token缺失", 201),
    PARAM_DEFECT_ERROR("参数缺失", 202),
    NOT_LOGIN_ERROR("未登陆", 203),
    SERVER_ERROR("服务器异常", 204),
    DATA_EXISTS("数据已存在", 205),
    DATA_NOT_EXISTS("数据不存在", 206),
    OPERATION_FREQUENTLY_ERROR("操作过频", 207),
    DATA_UNEQUAL_ERROR("值不相等", 208),
    NOT_SCHEDULED_ERROR("与期望效果不相符", 209),
    BEYOND_ERROR("超出范围", 210),
    SAMEDAYSUM_ERROR("当天查询次数已达到上限", 211),
    PAY_ERROR("未支付成功或网络延时，请稍后查询", 212),
    ADDCUSTOMER_EXISTS_ERROR("此客户已被添加", 213),
    MSG_CODE_ERROR("验证码错误或失效", 214),
    DATA_PROHIBIT("数据已禁用", 215),
    PHONE_ERROR("手机号不正确", 216),
    PASS_ERROR("密码或格式错误", 217),
    HASENTEREDWAREHOUSE("已入仓", 218),
    OUTOF_WAREHOUSE_ERROR("已出仓", 219),
    NOT_IN_WAREHOUSE_ERROR("未入仓", 220),
    NOT_OUTOF_WAREHOUSE_ERROR("未出仓", 220);

    //  成员变量
    private String message;
    private int code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //  构造方法
    ResultEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
