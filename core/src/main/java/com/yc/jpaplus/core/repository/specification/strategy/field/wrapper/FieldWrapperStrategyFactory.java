package com.yc.jpaplus.core.repository.specification.strategy.field.wrapper;

import com.yc.jpaplus.core.base.annoation.enums.Operator;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: TJM
 * @Date: 2020/3/25 20:34
 */
public class FieldWrapperStrategyFactory {
    private static Map<Operator,IFieldWrapperStrategy> fieldWrapperStrategys = new ConcurrentHashMap<>();

    static{
        IFieldWrapperStrategy commonWrapperStrategy = new CommonWrapperStratepy();

        fieldWrapperStrategys.put(Operator.EQUAL,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_EQUAL,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.LIKE,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_LIKE,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.LIKE_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_LIKE_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.STARTING,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_STARTING,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.ENDING,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_ENDING,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.GT,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.GT_OR_EQUAL,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.LT,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.LT_OR_EQUAL,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.STARTING_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_STARTING_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.ENDING_IN,commonWrapperStrategy);
        fieldWrapperStrategys.put(Operator.NOT_ENDING_IN,commonWrapperStrategy);

        fieldWrapperStrategys.put(Operator.BETWEEN_AND,new BetweenWrapperStrategy());
    }

    private FieldWrapperStrategyFactory(){}

    public static IFieldWrapperStrategy getFieldWrapperStrategy(Operator except){
        if (fieldWrapperStrategys.containsKey(except)) {
            return fieldWrapperStrategys.get(except);
        } else {
            throw new NoSuchElementException("can not found ConditionExcept type of " + except.name());
        }
    }
}