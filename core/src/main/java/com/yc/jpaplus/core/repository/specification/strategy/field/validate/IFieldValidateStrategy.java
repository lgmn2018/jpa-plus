package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

import java.lang.reflect.Field;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:40
 */
public interface IFieldValidateStrategy {
    ValidResult validate(Object dto, Field field, Class expectedClass);
}
