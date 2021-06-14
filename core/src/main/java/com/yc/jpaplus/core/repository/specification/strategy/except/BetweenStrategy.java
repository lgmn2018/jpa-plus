package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 17:09
 */
public class BetweenStrategy implements IExceptStrategy {
    final int betweenArgsSize = 2;
    @Override
    public Specification except(DtoFieldWrapper wrapper) {
        Object value = wrapper.getValue();
        if(value instanceof List) {
            List<String> list = (List<String>) value;
            if (list.size() == betweenArgsSize) {
                if(StringUtils.isNotEmpty(wrapper.getJoinName())){
                    return (root, query, cb) -> cb.between(
                            root.join(wrapper.getJoinName()).get(wrapper.getFieldName()),
                            list.get(0), list.get(1));
                }
                return (root, query, cb) -> cb.between(root.get(wrapper.getFieldName()), list.get(0), list.get(1));
            }else{
                throw new IllegalArgumentException("source:" + this.getClass() + " value incorrect length , get:" + list.size() + " expected:" + 2);
            }
        } else {
            throw new IllegalArgumentException("source:" + this.getClass() + " value type mismatch, get:" + value.getClass() + " expected:" + List.class);
        }
    }
}