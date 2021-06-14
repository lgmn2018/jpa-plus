package com.yc.jpaplus.example.dto.not_starting_in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressNotStartingInDto extends JpaPlusDto {
    @Condition(operator = Operator.NOT_STARTING_IN, likeInLogic = LikeInLogic.AND)
    private List<String> address;
}
