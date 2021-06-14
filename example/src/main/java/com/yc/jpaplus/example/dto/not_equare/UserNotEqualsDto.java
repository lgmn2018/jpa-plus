package com.yc.jpaplus.example.dto.not_equare;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class UserNotEqualsDto extends JpaPlusDto {
    @Condition(operator = Operator.NOT_EQUAL)
    private String name;
}
