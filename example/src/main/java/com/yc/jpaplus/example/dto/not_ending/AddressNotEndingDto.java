package com.yc.jpaplus.example.dto.not_ending;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressNotEndingDto extends JpaPlusDto {
    @Condition(operator = Operator.NOT_ENDING)
    private String address;
}
