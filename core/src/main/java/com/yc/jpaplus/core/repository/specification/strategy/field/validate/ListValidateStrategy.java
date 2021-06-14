package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

import com.yc.jpaplus.core.base.utils.RefUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 22:39
 */
public class ListValidateStrategy implements IFieldValidateStrategy {
    @Override
    public ValidResult validate(Object dto, Field field, Class expectedClass) {
        ValidResult validResult = new ValidResult();
        try {
            String fieldName = RefUtil.getFieldName(field);

            List<Class> actualType = RefUtil.getFieldActualType(field);

            Field expectedField = RefUtil.getDeclaredField(expectedClass, fieldName);

            Class fieldClass = field.getType();

            Class expectedFieldClass = expectedField.getType();

            if(actualType.size()==0 || actualType.size()>1){
                validResult.setMessage("field type is not match ,field:" + fieldName + " type:" + fieldClass.getTypeName() + " actulaType:" + actualType.get(0).getTypeName() +" expected:" + expectedFieldClass.getTypeName());
            }

            if(actualType.get(0).equals(expectedFieldClass)){
                validResult.setValid(true);
            }else{
                validResult.setMessage("field type is not match ,field:" + fieldName + " type:" + fieldClass.getTypeName() + " actulaType:" + actualType.get(0).getTypeName() +" expected:" + expectedFieldClass.getTypeName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            validResult.setMessage(field.getName() + " is not defined in " + expectedClass.getName());
        }

        return validResult;
    }
}