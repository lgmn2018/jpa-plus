package com.yc.jpaplus.example.dto.gt;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class UserGtDto extends JpaPlusDto {
    @Condition(operator = Operator.GT)
    Integer age;
}
