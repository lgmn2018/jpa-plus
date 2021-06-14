package com.yc.jpaplus.core.repository.specification.strategy.field.validate;

/**
 * @Author: TJM
 * @Date: 2020/3/25 21:50
 */

import lombok.Data;

@Data
public class ValidResult {
    String message;
    boolean isValid = false;
}