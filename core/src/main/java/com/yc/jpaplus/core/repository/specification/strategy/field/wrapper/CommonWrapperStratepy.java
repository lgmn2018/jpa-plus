package com.yc.jpaplus.core.repository.specification.strategy.field.wrapper;

import com.yc.jpaplus.core.base.utils.RefUtil;
import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:27
 */
public class CommonWrapperStratepy extends IFieldWrapperStrategy {
    @Override
    public DtoFieldWrapper wrapper(Object dto,Field field) {
        DtoFieldWrapper wrapper = null;
        try {
            Object fieldValue = RefUtil.getObjectValue(dto, field);
            wrapper = wrapper(field,fieldValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return wrapper;
    }
}