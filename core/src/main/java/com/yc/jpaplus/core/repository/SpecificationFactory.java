package com.yc.jpaplus.core.repository;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.ConditionLogic;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import com.yc.jpaplus.core.base.utils.RefUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:15
*/
public final class SpecificationFactory {

    /**
     * 模糊查询，匹配对应字段
     */
    @Condition(operator = Operator.STARTING)
    public static Specification begin(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value);
    }

    /**
     * 模糊查询，匹配对应字段
     */
    @Condition(operator = Operator.ENDING)
    public static Specification end(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), value + "%");
    }

    /**
     * 模糊查询，匹配对应字段
     */
    @Condition(operator = Operator.LIKE)
    public static Specification containsLike(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    @Condition(operator = Operator.LIKE_IN)
    public static Specification containsLikeIn(String attribute, LikeInLogic conditionContainLogic, ArrayList<String> value) {
        Specification specification = null;
        if (value.size() > 0) {
            for (String str : value) {
                if(specification==null){
                    specification=containsLike(attribute, str);
                }else {
                    if(conditionContainLogic.equals(LikeInLogic.AND)){
                        specification = specification.and(containsLike(attribute, str));
                    }else{
                        specification = specification.or(containsLike(attribute, str));
                    }
                }
            }
        }
        return specification;
    }

//    @Condition(except = ConditionExcept.CONTAIN_AND)
//    public static Specification containsLikeAnd(String attribute, ArrayList<String> value) {
//        Specification specification = null;
//        if (value.size() > 0) {
//            for (String str : value) {
//                if(specification==null){
//                    specification=containsLike(attribute, str);
//                }else {
//                    specification = specification.and(containsLike(attribute, str));
//                }
//            }
//        }
//        return specification;
//    }

    /**
     * 某字段的值等于 value 的查询条件
     */
    @Condition(operator = Operator.EQUAL)
    public static Specification equal(String attribute, Comparable value) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    /**
     * 某字段的值不等于 value 的查询条件
     */
    @Condition(operator = Operator.NOT_EQUAL)
    public static Specification notEqual(String attribute, Comparable value) {
        return (root, query, cb) -> cb.notEqual(root.get(attribute), value);
    }

    /**
     * 获取对应属性的值所在区间
     */
    @Condition(operator = Operator.BETWEEN_AND)
    public static Specification isBetween(String attribute, Comparable min, Comparable max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    /**
     * 通过属性名和集合实现 in 查询
     */
    @Condition(operator = Operator.IN)
    public static Specification in(String attribute, Object... c) {
        return (root, query, cb) -> root.get(attribute).in(c);
    }

    /**
     * 通过属性名和集合实现 in 查询
     */
    @Condition(operator = Operator.NOT_IN)
    public static Specification notIn(String attribute, Object... c) {
        return (root, query, cb) -> cb.not(root.get(attribute).in(c));
    }
    /**
     * 通过属性名构建大于等于 Value 的查询条件
     */
    @Condition(operator = Operator.GT)
    public static Specification greaterThan(String attribute, Comparable value) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
    }

    /**
     * 通过属性名构建大于等于 Value 的查询条件
     */
    @Condition(operator = Operator.GT_OR_EQUAL)
    public static Specification greaterThanOrEqualTo(String attribute, Comparable value) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), value);
    }

    /**
     * 通过属性名构建小于等于 Value 的查询条件
     */
    @Condition(operator = Operator.LT)
    public static Specification lessThan(String attribute, Comparable value) {
        return (root, query, cb) -> cb.lessThan(root.get(attribute), value);
    }

    /**
     * 通过属性名构建小于等于 Value 的查询条件
     */
    @Condition(operator = Operator.LT_OR_EQUAL)
    public static Specification lessThanOrEqualTo(String attribute, Comparable value) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
    }

    public static Specification exec(Specification specification, ConditionLogic conditionLogic, Operator conditionExcept, LikeInLogic conditionContainLogic, String fieldName, Class fieldClazz, Object... values) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clz = SpecificationFactory.class;
        //获取SpecificationFactory类定义的方法
        Method[] methods = clz.getDeclaredMethods();
        //方法的ConditionExcept值
        Operator methodExcept;

        Method invokeMethod = null;

        //通过方法的ConditionExcept值查找相应的方法
        for (Method method : methods) {
            if (method.getAnnotation(Condition.class) != null) {
                methodExcept = method.getAnnotation(Condition.class).operator();
                if (methodExcept.equals(conditionExcept)) {
                    invokeMethod = method;
                }
            }
        }

        if (invokeMethod != null) {
            if (conditionExcept.equals(Operator.BETWEEN_AND)) {
                //通过方法名、参数类型获取最终要执行的方法
                invokeMethod = clz.getDeclaredMethod(invokeMethod.getName(), String.class, Comparable.class, Comparable.class);

                //通过ConditionLogic判断 and / or
                if (specification != null && conditionLogic.equals(ConditionLogic.AND)) {

                    specification = specification.and((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values[0], values[1]));

                } else if (specification != null && conditionLogic.equals(ConditionLogic.OR)) {

                    specification = specification.or((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values[0], values[1]));

                } else {

                    specification = (Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values[0], values[1]);

                }
            } else if (conditionExcept.equals(Operator.IN)) {

                invokeMethod = clz.getDeclaredMethod(invokeMethod.getName(), String.class, Object[].class);

                //通过ConditionLogic判断 and / or
                if (specification != null && conditionLogic.equals(ConditionLogic.AND)) {

                    specification = specification.and((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values));

                } else if (specification != null && conditionLogic.equals(ConditionLogic.OR)) {

                    specification = specification.or((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values));

                } else {

                    specification = (Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values);

                }

            } else if (conditionExcept.equals(Operator.NOT_IN)) {

                invokeMethod = clz.getDeclaredMethod(invokeMethod.getName(), String.class, Object[].class);

                //通过ConditionLogic判断 and / or
                if (specification != null && conditionLogic.equals(ConditionLogic.AND)) {

                    specification = specification.and((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values));

                } else if (specification != null && conditionLogic.equals(ConditionLogic.OR)) {

                    specification = specification.or((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values));

                } else {

                    specification = (Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, values);

                }

            } else if (conditionExcept.equals(Operator.LIKE_IN)) {

                invokeMethod = clz.getDeclaredMethod(invokeMethod.getName(), String.class, LikeInLogic.class, ArrayList.class);

                //通过conditionContainLogic判断 CONTAIN_AND / CONTAIN_OR
                if (specification != null && conditionLogic.equals(ConditionLogic.AND)) {

                    specification = specification.and((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, conditionContainLogic, values[0]));

                } else if (specification != null && conditionLogic.equals(ConditionLogic.OR)) {

                    specification = specification.or((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, conditionContainLogic, values[0]));

                } else {

                    specification = (Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, conditionContainLogic, values[0]);

                }

            }else {
                //通过方法名、参数类型获取最终要执行的方法
                if (conditionExcept.equals(Operator.EQUAL) || conditionExcept.equals(Operator.NOT_EQUAL)) {

                    invokeMethod = clz.getDeclaredMethod(invokeMethod.getName(), String.class, Comparable.class);

                }

                //通过ConditionLogic判断 and / or
                Object obj = values[0];
                if (specification != null && conditionLogic.equals(ConditionLogic.AND)) {

                    specification = specification.and((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, obj));

                } else if (specification != null && conditionLogic.equals(ConditionLogic.OR)) {

                    specification = specification.or((Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, obj));

                } else {

                    specification = (Specification) invokeMethod.invoke(new SpecificationFactory(), fieldName, obj);

                }
            }
        } else {
            new NoSuchMethodException();
        }
        return specification;
    }

    public static <T extends JpaPlusDto> Specification getSpecification(T dto) throws Exception {
        Class<?> clz = dto.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();

        HashMap<String, List<Object>> objects = new HashMap<>();
        Specification specification = null;

        for (Field field : fields) {
            Object fieldValue = RefUtil.getObjectValue(dto, field);

            Annotation annotations = field.getAnnotation(Condition.class);

            if (fieldValue != null
                    && (!((Condition) annotations).ignoreBlank() || (((Condition) annotations).ignoreBlank() && !StringUtils.isEmpty(fieldValue)))
                    && (!((Condition) annotations).ignoreZero() || (((Condition) annotations).ignoreZero() && ((Integer)fieldValue)!=0))) {
//                Annotation annotations = field.getAnnotation(Condition.class);
                Class fieldClazz = Class.forName(field.getType().getName());

                ConditionLogic logic = ((Condition) annotations).logic();

                Operator except = ((Condition) annotations).operator();

                if (except.equals(Operator.BETWEEN_AND)) {
                    String fieldName = ((Condition) annotations).field();

                    if (!objects.containsKey(fieldName)) {
                        List<Object> betweenValueList = new ArrayList<>();

                        betweenValueList.add(fieldValue);

                        objects.put(fieldName, betweenValueList);
                    } else {
                        List<Object> betweenValueList = objects.get(fieldName);

                        betweenValueList.add(fieldValue);

                        betweenValueList.sort((arg0, arg1) -> {
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

                        specification = SpecificationFactory.exec(specification, logic, except,null, fieldName, fieldClazz, betweenValueList.toArray());
                    }

                } else if (except.equals(Operator.IN)) {

                    String fieldName = ((Condition) annotations).field();

                    if(StringUtils.isEmpty(fieldName)){
                        fieldName = field.getName();
                    }

                    if(((ArrayList<Object>) fieldValue).toArray().length>0) {

                        specification = SpecificationFactory.exec(specification, logic, except, null, fieldName, fieldClazz, ((ArrayList<Object>) fieldValue).toArray());

                    }
                }else if (except.equals(Operator.LIKE_IN)) {
//                    if(((ArrayList<Object>)(((ArrayList<Object>) fieldValue).toArray()[0])).toArray().length>0) {

                    String fieldName = ((Condition) annotations).field();

                    if(StringUtils.isEmpty(fieldName)){
                        fieldName = field.getName();
                    }

                    if(((ArrayList<Object>) fieldValue).toArray().length>0) {
                        LikeInLogic conditionContainLogic = ((Condition) annotations).likeInLogic();

                        specification = SpecificationFactory.exec(specification, logic, except, conditionContainLogic, fieldName, fieldClazz, fieldValue);
                    }

                } else {

                    String fieldName = ((Condition) annotations).field();

                    if(StringUtils.isEmpty(fieldName)){
                        fieldName = field.getName();
                    }

                    specification = SpecificationFactory.exec(specification, logic, except, null, fieldName, fieldClazz, fieldValue);

                }
            }
        }
        return specification;
    }
}