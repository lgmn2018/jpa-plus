package com.yc.jpaplus.core.repository.specification;

import com.yc.jpaplus.core.repository.specification.strategy.logic.LogicStrategyFactory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/25 20:24
 */
public class SpecificationConstructor {
    public static Specification getSpecification(Object dto) {
        Specification specification = null;
        if(!DtoValidator.valid(dto)){
            return null;
        }

        List<DtoFieldWrapper> fieldWrappers = DtoDecorator.wrap(dto);
        for (DtoFieldWrapper fieldWrapper : fieldWrappers) {
            specification = LogicStrategyFactory.getLogic(fieldWrapper.getLogic()).logic(specification,fieldWrapper);
        }
        return specification;
    }
}