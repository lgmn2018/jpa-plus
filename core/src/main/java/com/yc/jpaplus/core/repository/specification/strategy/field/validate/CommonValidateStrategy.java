package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

import com.yc.jpaplus.core.base.utils.RefUtil;

import java.lang.reflect.Field;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:45
 */
public class CommonValidateStrategy implements IFieldValidateStrategy {
    @Override
    public ValidResult validate(Object dto, Field field,Class expectedClass) {
        ValidResult validResult = new ValidResult();
        try {
            String fieldName = RefUtil.getFieldName(field);

            Field expectedField = RefUtil.getDeclaredField(expectedClass, fieldName);

            Class fieldClass = field.getType();
            Class expectedFieldClass = expectedField.getType();

            if(fieldClass.equals(expectedFieldClass)){
                validResult.setValid(true);
            }else{
                validResult.setMessage("field type is not match ,field:" + fieldName + " actual:" + fieldClass.getTypeName() + " expected:" + expectedFieldClass.getTypeName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            validResult.setMessage(field.getName() + " is not defined in " + expectedClass.getName());
        }

        return validResult;
    }
}