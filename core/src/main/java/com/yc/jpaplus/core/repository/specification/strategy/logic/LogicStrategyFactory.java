package com.yc.jpaplus.core.repository.specification.strategy.logic;

import com.yc.jpaplus.core.base.annoation.enums.ConditionLogic;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: TJM
 * @Date: 2020/3/25 17:59
 */
public class LogicStrategyFactory {

    private static Map<ConditionLogic,ILogicStrategy> logicStrategys=new ConcurrentHashMap<>();

    static{
        logicStrategys.put(ConditionLogic.AND,new AndStrategy());
        logicStrategys.put(ConditionLogic.OR,new OrStrategy());
    }

    private LogicStrategyFactory(){}

    public static ILogicStrategy getLogic(ConditionLogic conditionLogic){
        if (logicStrategys.containsKey(conditionLogic)) {
            return logicStrategys.get(conditionLogic);
        } else {
            throw new NoSuchElementException("can not found ConditionLogic type of " + conditionLogic.name());
        }
    }
}