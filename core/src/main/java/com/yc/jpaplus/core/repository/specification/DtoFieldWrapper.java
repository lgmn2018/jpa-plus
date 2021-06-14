package com.yc.jpaplus.core.repository.specification;

import com.yc.jpaplus.core.base.annoation.enums.ConditionLogic;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author: TJM
 * @Date: 2020/3/25 16:36
 */
@Data
public class DtoFieldWrapper {
    private Field field;
    private String fieldName;
    private Object value;
    private ConditionLogic logic;
    private Operator except;
    private LikeInLogic likeInLogic;
    private boolean ignoreEmpty;
    private boolean ignoreZero;
    private boolean ignoreBlank;
    private String joinName;
}