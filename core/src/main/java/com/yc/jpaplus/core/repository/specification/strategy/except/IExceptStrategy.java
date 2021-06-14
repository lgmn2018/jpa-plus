package com.yc.jpaplus.core.repository.specification.strategy.except;

import com.yc.jpaplus.core.repository.specification.DtoFieldWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * @Author: TJM
 * @Date: 2020/3/24 19:25
 */
public interface IExceptStrategy {
    Specification except(DtoFieldWrapper wrapper);
}
