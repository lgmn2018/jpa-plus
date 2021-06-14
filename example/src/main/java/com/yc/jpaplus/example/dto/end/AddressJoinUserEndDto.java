package com.yc.jpaplus.example.dto.end;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressJoinUserEndDto extends JpaPlusDto {
    @Condition(field = "user.name",operator = Operator.ENDING)
    private String name;
}
