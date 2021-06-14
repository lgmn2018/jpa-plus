package com.yc.jpaplus.core.repository.specification.strategy.logic;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Author: TJM
 * @Date: 2020/3/24 18:16
 */
public interface ILogicStrategy {
    Specification logic(Specification specification, DtoFieldWrapper wrapper);
}