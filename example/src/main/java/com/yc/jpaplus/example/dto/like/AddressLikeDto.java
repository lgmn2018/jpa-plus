package com.yc.jpaplus.example.dto.like;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressLikeDto extends JpaPlusDto {
    @Condition(operator = Operator.LIKE)
    private String address;
}
