package com.yc.jpaplus.example.dto.lt;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressJoinUserLtDto extends JpaPlusDto {
    @Condition(field = "user.age", operator = Operator.LT)
    Integer age;
}
