package com.yc.jpaplus.core.base.annoation;

import com.yc.jpaplus.core.base.annoation.enums.ConditionLogic;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Author: TJM
* @Date: 2020/3/18 15:18
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Condition {

    /**
     * 是否忽略空集合，忽略后传入空值（集合长度为0/集合为空）该属性不会作为查询条件
     * true:忽略
     * false:不忽略
     * @return
     */
    boolean ignoreEmpty() default false;

    /**
     * 是否忽略数字 0 值，忽略后传入0值该属性不会作为查询条件
     * true:忽略
     * false:不忽略
     * @return
     */
    boolean ignoreZero() default false;

    /**
     * 是否忽略字符空值，忽略后传入空值（字符长度为0/字符为空）值该属性不会作为查询条件
     * true:忽略
     * false:不忽略
     * @return
     */
    boolean ignoreBlank() default false;

    /**
     *  and or
     */
    ConditionLogic logic() default ConditionLogic.AND;


    /**
     *  = <> (in/not in) (like/not like) (between and)
     */
    Operator operator() default Operator.EQUAL;


    /**
     * 设置LIKE_IN  的 AND / OR
     * @return
     */
    LikeInLogic likeInLogic() default LikeInLogic.AND;

    /**
     * 当except=BETWEEN_AND时有效
     * 且需要成对出现
     * 需要提供属性名称
     */
    String field() default "";

    /**
     * 当except=BETWEEN_AND时有效
     * 把属性定义为最小值
     * @return
     */
    @Deprecated
    boolean isMin() default false;

    /**
     * 当except=BETWEEN_AND时有效
     * 把属性定义为最大值
     * @return
     */
    @Deprecated
    boolean isMax() default false;
}
