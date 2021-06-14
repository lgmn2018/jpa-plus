package com.yc.jpaplus.example.dto.not_starting;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressJoinUserNotStartingDto extends JpaPlusDto {
    @Condition(field = "user.name", operator = Operator.NOT_STARTING)
    String name;
}
