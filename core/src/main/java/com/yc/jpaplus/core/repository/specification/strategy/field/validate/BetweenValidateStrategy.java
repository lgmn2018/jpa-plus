package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

import com.yc.jpaplus.core.base.utils.RefUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 22:49
 */
public class BetweenValidateStrategy implements IFieldValidateStrategy {
    @Override
    public ValidResult validate(Object dto, Field field, Class expectedClass) {
        ValidResult validResult = new ValidResult();
        try {
            Object value = RefUtil.getObjectValue(dto, field);

            if(!(value instanceof List)) {
                validResult.setMessage("field type is not match, field type is " + field.getGenericType() + " expected: List<?>");
                return validResult;
            }
            List valueList = (List) value;

            int valueListLength = 2;

            if(valueList.size()!=valueListLength){
                validResult.setMessage("field type is not match, value's size is " + valueList.size() + " expected:" + valueListLength);
                return validResult;
            }

            validResult = new ListValidateStrategy().validate(dto, field, expectedClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return validResult;
    }
}