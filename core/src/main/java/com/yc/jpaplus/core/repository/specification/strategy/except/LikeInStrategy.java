package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 15:55
 */
public class LikeInStrategy implements IExceptStrategy {
    @Override
    public Specification except(DtoFieldWrapper wrapper) {
        Specification specification = null;
        Object value = wrapper.getValue();
        if (value instanceof List) {
            List<String> list = (List<String>) value;
            if (list.size() > 0) {
                String fieldName = wrapper.getFieldName();
                String joinName = wrapper.getJoinName();
                IExceptStrategy exceptStrategy = ExceptStrategyFactory.getExceptStrategy(Operator.LIKE);
                for (String str : list) {
                    DtoFieldWrapper dtoWrapper = new DtoFieldWrapper();
                    if(StringUtils.isNotEmpty(joinName)) {
                        dtoWrapper.setJoinName(joinName);
                    }
                    dtoWrapper.setFieldName(fieldName);
                    dtoWrapper.setValue(str);
                    if (specification == null) {
                        specification = exceptStrategy.except(dtoWrapper);
                    } else {
                        if (wrapper.getLikeInLogic().equals(LikeInLogic.AND)) {
                            specification = specification.and(exceptStrategy.except(dtoWrapper));
                        } else if (wrapper.getLikeInLogic().equals(LikeInLogic.OR)) {
                            specification = specification.or(exceptStrategy.except(dtoWrapper));
                        }else{
                            throw  new IllegalArgumentException("source:" + this.getClass() + " dtoWrapper mising LikeInLogic" );
                        }
                    }
                }
            }
            return specification;
        } else {
            throw new IllegalArgumentException("source:" + this.getClass() + " value type mismatch, get:" + value.getClass() + " expected:" + List.class);
        }
    }
}