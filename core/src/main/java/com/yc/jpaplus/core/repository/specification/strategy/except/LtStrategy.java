package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Author: TJM
 * @Date: 2020/3/25 17:25
 */
public class LtStrategy implements IExceptStrategy {
    @Override
    public Specification except(DtoFieldWrapper wrapper) {
        Object value = wrapper.getValue();
        if(value instanceof Comparable) {
            Comparable val = (Comparable)value;
            if(StringUtils.isNotEmpty(wrapper.getJoinName())){
                return (root, query, cb) -> cb.lessThan(root.join(wrapper.getJoinName()).get(wrapper.getFieldName()), val);
            }
            return (root, query, cb) -> cb.lessThan(root.get(wrapper.getFieldName()), val);
        }else {
            throw new IllegalArgumentException("source:" + this.getClass() + " value type mismatch, get:" + value.getClass() + " expected:" + Comparable.class);
        }
    }
}