package com.yc.jpaplus.example.dto.lt_or_equare;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressJoinUserLtOrEquareDto extends JpaPlusDto {
    @Condition(field = "user.age", operator = Operator.LT_OR_EQUAL)
    Integer age;
}
