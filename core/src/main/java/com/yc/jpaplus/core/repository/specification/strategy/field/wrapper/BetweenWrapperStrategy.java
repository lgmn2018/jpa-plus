package com.yc.jpaplus.core.repository.specification.strategy.field.wrapper;

import com.yc.jpaplus.core.base.utils.RefUtil;
import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 20:45
 */
public class BetweenWrapperStrategy extends IFieldWrapperStrategy {
    @Override
    public DtoFieldWrapper wrapper(Object dto, Field field) {
        DtoFieldWrapper wrapper = new DtoFieldWrapper();
        try {
            Object fieldValue = RefUtil.getObjectValue(dto, field);
            List values = (ArrayList)fieldValue;
            Object value1 = values.get(0);
            Class fieldClazz = value1.getClass();
            values.sort((arg0,arg1)->{
                if (fieldClazz.equals(Date.class)) {
                    Date d1 = (Date) arg0;
                    Date d2 = (Date) arg1;
                    return d1.compareTo(d2);
                } else if (fieldClazz.equals(Integer.class)) {
                    Integer d1 = (Integer) arg0;
                    Integer d2 = (Integer) arg1;
                    return d1.compareTo(d2);
                } else if (fieldClazz.equals(BigDecimal.class)) {
                    BigDecimal d1 = (BigDecimal) arg0;
                    BigDecimal d2 = (BigDecimal) arg1;
                    return d1.compareTo(d2);
                } else if (fieldClazz.equals(Long.class)) {
                    Long d1 = (Long) arg0;
                    Long d2 = (Long) arg1;
                    return d1.compareTo(d2);
                } else if (fieldClazz.equals(Double.class)) {
                    Double d1 = (Double) arg0;
                    Double d2 = (Double) arg1;
                    return d1.compareTo(d2);
                } else if (fieldClazz.equals(Timestamp.class)) {
                    Timestamp d1 = (Timestamp) arg0;
                    Timestamp d2 = (Timestamp) arg1;
                    return d1.compareTo(d2);
                } else {
                    return 0;
                }
            });
            wrapper = wrapper(field,values);
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