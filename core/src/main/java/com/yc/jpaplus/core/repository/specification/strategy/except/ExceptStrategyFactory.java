package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.base.annoation.enums.Operator;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: TJM
 * @Date: 2020/3/25 16:00
 */
public class ExceptStrategyFactory {
    private static Map<Operator, IExceptStrategy> exceptStrategys = new ConcurrentHashMap<>();

    static {
        exceptStrategys.put(Operator.EQUAL, new EquareStrategy());
        exceptStrategys.put(Operator.NOT_EQUAL, new NotEquareStrategy());
        exceptStrategys.put(Operator.IN, new InStrategy());
        exceptStrategys.put(Operator.NOT_IN, new NotInStrategy());
        exceptStrategys.put(Operator.LIKE, new LikeStrategy());
        exceptStrategys.put(Operator.NOT_LIKE, new NotLikeStrategy());
        exceptStrategys.put(Operator.STARTING, new StartingStrategy());
        exceptStrategys.put(Operator.NOT_STARTING, new NotStartingStrategy());
        exceptStrategys.put(Operator.ENDING, new EndingStrategy());
        exceptStrategys.put(Operator.NOT_ENDING, new NotEndingStrategy());
        exceptStrategys.put(Operator.LIKE_IN, new LikeInStrategy());
        exceptStrategys.put(Operator.NOT_LIKE_IN, new NotLikeInStrategy());
        exceptStrategys.put(Operator.BETWEEN_AND, new BetweenStrategy());
        exceptStrategys.put(Operator.GT, new GtStrategy());
        exceptStrategys.put(Operator.GT_OR_EQUAL, new GtOrEquareStrategy());
        exceptStrategys.put(Operator.LT, new LtStrategy());
        exceptStrategys.put(Operator.LT_OR_EQUAL, new LtOrEquareStrategy());
        exceptStrategys.put(Operator.STARTING_IN, new StartingInStrategy());
        exceptStrategys.put(Operator.NOT_STARTING_IN, new NotStartingInStrategy());
        exceptStrategys.put(Operator.ENDING_IN, new EndingInStrategy());
        exceptStrategys.put(Operator.NOT_ENDING_IN, new NotEndingInStrategy());
    }

    private ExceptStrategyFactory() {
    }

    public static IExceptStrategy getExceptStrategy(Operator conditionExcept) {
        if (exceptStrategys.containsKey(conditionExcept)) {
            return exceptStrategys.get(conditionExcept);
        } else {
            throw new NoSuchElementException("can not found ConditionExcept type of " + conditionExcept.name());
        }
    }

}