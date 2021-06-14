package com.yc.jpaplus.core.repository.specification;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import com.yc.jpaplus.core.repository.exception.DtoValidException;
import com.yc.jpaplus.core.repository.specification.strategy.field.validate.FieldValidateStrategyFactory;
import com.yc.jpaplus.core.repository.specification.strategy.field.validate.ValidResult;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:38
 */
@Slf4j
public class DtoValidator {

    public static boolean valid(Object dto) {
        Class<?> clz = dto.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();

        Type t = dto.getClass().getGenericSuperclass();

        if(dto instanceof JpaPlusDto){
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class actualType = (Class) p[0];
                for (Field field : fields) {
                    Annotation annotation = field.getAnnotation(Condition.class);
                    ValidResult result = FieldValidateStrategyFactory.getFieldValidateStrategy(((Condition) annotation).operator()).validate(dto, field, actualType);
                    if(!result.isValid()){
                        log.error(result.getMessage());
                        throw new DtoValidException(result.getMessage());
                    }
                }
            }
        }else {
            return false;
        }

//        if (t != null) {
//            Class superClass = (Class) t;
//            /**
//             * 判断是否继承自JpaPlusDto
//             * 只验证继承自JpaPlusDto的Dto对象
//             */
//            if (superClass.equals(JpaPlusDto.class)) {
//                if (t instanceof ParameterizedType) {
//                    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
//                    Class actualType = (Class) p[0];
//                    for (Field field : fields) {
//                        Annotation annotation = field.getAnnotation(Condition.class);
//                        ValidResult result = FieldValidateStrategyFactory.getFieldValidateStrategy(((Condition) annotation).except()).validate(dto, field, actualType);
//                        if(!result.isValid()){
//                            log.error(result.getMessage());
//                            throw new DtoValidException(result.getMessage());
//                        }
//                    }
//                }
//            }else {
//                return false;
//            }
//        }
        return true;
    }
}