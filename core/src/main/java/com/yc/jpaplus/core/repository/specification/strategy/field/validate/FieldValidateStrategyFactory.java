package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

import com.yc.jpaplus.core.base.annoation.enums.Operator;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: TJM
 * @Date: 2020/3/25 18:47
 */
public class FieldValidateStrategyFactory {
    private static Map<Operator, IFieldValidateStrategy> fieldValidateStrategys = new ConcurrentHashMap<>();

    static{
        fieldValidateStrategys.put(Operator.EQUAL,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_EQUAL,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.LIKE,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_LIKE,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.STARTING,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_STARTING,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.ENDING,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_ENDING,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.GT,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.GT_OR_EQUAL,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.LT,new CommonValidateStrategy());
        fieldValidateStrategys.put(Operator.LT_OR_EQUAL,new CommonValidateStrategy());

        fieldValidateStrategys.put(Operator.IN,new ListValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_IN,new ListValidateStrategy());
        fieldValidateStrategys.put(Operator.LIKE_IN,new ListValidateStrategy());
        fieldValidateStrategys.put(Operator.NOT_LIKE_IN,new ListValidateStrategy());


        fieldValidateStrategys.put(Operator.BETWEEN_AND,new BetweenValidateStrategy());
    }

    private FieldValidateStrategyFactory(){}

    public static IFieldValidateStrategy getFieldValidateStrategy(Operator conditionLogic){
        if (fieldValidateStrategys.containsKey(conditionLogic)) {
            return fieldValidateStrategys.get(conditionLogic);
        } else {
            throw new NoSuchElementException("can not found ConditionLogic type of " + conditionLogic.name());
        }
    }
}