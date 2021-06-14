package com.yc.jpaplus.core.base.dto;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JpaPlusDto<T> {

    Class clazz;

    Class entityClass;

    public JpaPlusDto() {
        clazz = this.getClass();
    }

    public T transDtoToEntity() {
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
        T entity = null;
        try {
            entity = (T) this.entityClass.newInstance();
            BeanUtils.copyProperties(this, entity);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Class ft = field.getType();
                if (ft != null && ft.getSuperclass() != null && ft.getSuperclass().equals(JpaPlusDto.class)) {
                    Field declaredField = entity.getClass().getDeclaredField(field.getName());
                    field.setAccessible(true);
                    Object value = field.getType().getMethod("transDtoToEntity").invoke(field.get(this));
                    declaredField.setAccessible(true);
                    declaredField.set(entity, value);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
