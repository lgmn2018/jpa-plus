package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Author: TJM
 * @Date: 2020/3/25 15:54
 */
public class LikeStrategy implements IExceptStrategy {
    @Override
    public Specification except(DtoFieldWrapper wrapper) {
        if(StringUtils.isNotEmpty(wrapper.getJoinName())){
            return (root, query, cb) -> cb.like(root.join(wrapper.getJoinName()).get(wrapper.getFieldName()), "%" + wrapper.getValue() + "%");
        }
        return (root, query, cb) -> cb.like(root.get(wrapper.getFieldName()), "%" + wrapper.getValue() + "%");
    }
}