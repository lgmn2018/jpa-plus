package com.yc.jpaplus.core.repository.specification.strategy.logic;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import com.yc.jpaplus.core.repository.specification.strategy.except.ExceptStrategyFactory;
import com.yc.jpaplus.core.repository.specification.strategy.except.IExceptStrategy;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Author: TJM
 * @Date: 2020/3/24 19:32
 */
public class AndStrategy implements ILogicStrategy {
    @Override
    public Specification logic(Specification specification, DtoFieldWrapper wrapper) {
        IExceptStrategy exceptStrategy = ExceptStrategyFactory.getExceptStrategy(wrapper.getExcept());
        if (specification==null){
            return exceptStrategy.except(wrapper);
        }else{
            return specification.and(exceptStrategy.except(wrapper));
        }
    }
}