package com.yc.jpaplus.core.base.utils;

import com.yc.jpaplus.core.base.annoation.Condition;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @Author: TJM
 * @Date: 2020/3/18 15:15
 */
public class RefUtil {
    public static String getFieldName(Field field){
        Annotation annotation = field.getAnnotation(Condition.class);
        String fieldName = ((Condition) annotation).field();
        if(StringUtils.isEmpty(fieldName)){
            fieldName = field.getName();
        }
        return fieldName;
    }

    public static List<Class> getFieldActualType(Field field){
        Type t = field.getGenericType();
//        Class result = (Class) t;
        List<Class> result = new ArrayList<>();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;

            Type[] types = pt.getActualTypeArguments();

            if (types != null && types.length > 0) {
                for (Type type : types) {
                    result.add((Class) type);
                }
            }
        }
        return result;
    }

    public static Field getDeclaredField(Class clazz, String fieldName) throws NoSuchFieldException {
        Field field = null ;

        Class tempClass = clazz;

        for(; tempClass != Object.class ; tempClass = tempClass.getSuperclass()) {
            Field[] fields = tempClass.getDeclaredFields();

            for (Field fieldItem : fields) {
                if(fieldItem.getName().equals(fieldName)){
                    field = fieldItem;
                    break;
                }
            }

            if(field!=null){
                break;
            }
        }
        if(field==null){
            throw new NoSuchFieldException(field.getName() + " is not defined");
        }
        return field;
    }

    public static List<Field> getDeclaredFields(Class clazz) {
        Class tempClass = clazz;

        List<Field> allFields = new ArrayList<>();

        for(; tempClass != Object.class ; tempClass = tempClass.getSuperclass()) {
            Field[] fields = tempClass.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
        }
        return allFields;
    }

    public static Object getObjectValue(Object object, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object res = null;

        if (object != null) {
            Method m = object.getClass().getMethod(
                    "get" + getMethodName(field.getName()));
            Object val = m.invoke(object);
            if (val != null) {
                res = val;
            }
        }
        return res;
    }

    public static void setObjectValue(Object object, Field field, Object value) throws Exception {
        Method m = object.getClass().getMethod(
                "set" + getMethodName(field.getName()), field.getType());

        m.invoke(object, value);
    }

    public static Field[] getFields(Object object) {
        if (object != null) {
            // 拿到该类
            Class<?> clz = object.getClass();
            // 获取实体类的所有属性，返回Field数组
//            Field[] fields = clz.getDeclaredFields();
//            Field[] fields1 = clz.getSuperclass().getSuperclass().getDeclaredFields();
//             clz.getSuperclass().getDeclaredFields();
            Field[] fields = new Field[]{};
            while (clz != null) {
                fields = ArrayUtils.addAll(fields, clz.getDeclaredFields());
                clz = clz.getSuperclass();
            }
//            Field[] fields = ArrayUtils.addAll(clz.getDeclaredFields(), clz.getSuperclass().getDeclaredFields());
            return fields;
        } else {
            return null;
        }
    }

    public static <T extends Serializable> T MapToObject(Map<String, String> map, Class<T> clazz) {
        Set<String> keys = map.keySet();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Boolean nextLoop = false;
        for (String key : keys) {
            nextLoop = false;
            for (Field f : fields) {
                try {
                    String fieldNmae = f.getName();
                    if (key.equals(fieldNmae) && map.get(key) != null) {
                        String methodName = getMethodName(fieldNmae);
                        Method m = object.getClass().getMethod("set" + methodName, f.getType());
                        m.invoke(object, map.get(key));
                        nextLoop = true;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (nextLoop) {
                    break;
                }
            }
        }

        return object;
    }

    private static String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        String methodName = new String(items);
        return methodName;
    }
}
