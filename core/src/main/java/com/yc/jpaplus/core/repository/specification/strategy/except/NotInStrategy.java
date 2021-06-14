package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 17:16
 */
public class NotInStrategy implements IExceptStrategy {
    @Override
    public Specification except(DtoFieldWrapper wrapper) {
        Object value = wrapper.getValue();
        if (value instanceof List) {
            List<String> list = (List<String>) value;
            if (list.size() > 0) {
                if(StringUtils.isNotEmpty(wrapper.getJoinName())){
                    return (root, query, cb) -> cb.not(root.join(wrapper.getJoinName()).get(wrapper.getFieldName()).in(list.toArray()));
                }
                return (root, query, cb) -> cb.not(root.get(wrapper.getFieldName()).in(list.toArray()));
            } else {
                throw new IllegalArgumentException("source:" + this.getClass() + " value incorrect length , get:" + list.size() + " expected: >0");
            }
        } else {
            throw new IllegalArgumentException("source:" + this.getClass() + " value type mismatch, get:" + value.getClass() + " expected:" + List.class);
        }
    }
}