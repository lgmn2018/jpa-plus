package com.yc.jpaplus.core.repository.specification;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.utils.RefUtil;
import com.yc.jpaplus.core.repository.specification.strategy.field.wrapper.FieldWrapperStrategyFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 23:22
 */
@Slf4j
public class DtoDecorator {

    public static List<DtoFieldWrapper> wrap(Object dto){
        List<DtoFieldWrapper> list = new ArrayList<>();
        Class<?> clz = dto.getClass();
        // 获取实体类的所有属性，返回Field数组
        List<Field> fields = RefUtil.getDeclaredFields(clz);
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Condition.class);
            if(annotation==null) {
                continue;
            }
            Operator except = ((Condition) annotation).operator();
            list.add(FieldWrapperStrategyFactory.getFieldWrapperStrategy(except).wrapper(dto, field));
        }

        list = filterWrapper(list);

        return list;
    }

    private static List<DtoFieldWrapper> filterWrapper(List<DtoFieldWrapper> list){
        List<DtoFieldWrapper> result = new ArrayList<>();

        for (DtoFieldWrapper wrapper : list) {
            Object value = wrapper.getValue();
            if(null == value){
                continue;
            }
            if(value instanceof List){
                boolean ignoreEmpty = wrapper.isIgnoreEmpty();
                if (ignoreEmpty && ((List)value).size() == 0){
                    continue;
                }
            }

            if(value instanceof String){
                boolean ignoreEmpty = wrapper.isIgnoreEmpty();
                if (ignoreEmpty && ((String)value).length() == 0){
                    continue;
                }
            }

            if(value instanceof Number){
                boolean ignoreEmpty = wrapper.isIgnoreEmpty();
                if (ignoreEmpty && ((Number)value).intValue() == 0){
                    continue;
                }
            }
            result.add(wrapper);
        }

        return result;
    }
}