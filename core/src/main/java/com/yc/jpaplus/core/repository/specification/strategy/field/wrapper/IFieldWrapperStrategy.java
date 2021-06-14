package com.yc.jpaplus.core.repository.specification.strategy.field.wrapper;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.ConditionLogic;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:24
 */
public abstract class IFieldWrapperStrategy {
    public abstract DtoFieldWrapper wrapper(Object dto, Field field);

    DtoFieldWrapper wrapper(Field field,Object value){
        DtoFieldWrapper wrapper = new DtoFieldWrapper();
        String fieldName = field.getName();
        Annotation annotation = field.getAnnotation(Condition.class);

        if (annotation==null){
            return null;
        }

        Condition condition = (Condition) annotation;
        ConditionLogic logic = condition.logic();
        Operator except = condition.operator();
        LikeInLogic likeInLogic = condition.likeInLogic();
        boolean ignoreEmpty = condition.ignoreEmpty();
        boolean ignoreZero = condition.ignoreZero();
        boolean ignoreBlank = condition.ignoreBlank();

        wrapper.setField(field);
        wrapper.setFieldName(fieldName);
        wrapper.setValue(value);
        wrapper.setLogic(logic);
        wrapper.setExcept(except);
        wrapper.setLikeInLogic(likeInLogic);
        wrapper.setIgnoreEmpty(ignoreEmpty);
        wrapper.setIgnoreZero(ignoreZero);
        wrapper.setIgnoreBlank(ignoreBlank);

        String conditionField = condition.field();
        String[] joinField = conditionField.split("\\.");
        if(joinField.length==2){
            wrapper.setJoinName(joinField[0]);
            wrapper.setFieldName(joinField[1]);
        }

        return wrapper;
    }
}
