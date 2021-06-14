package com.yc.jpaplus.example.dto.not_ending_in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserNotEndingInDto extends JpaPlusDto {
    @Condition(field = "user.name", operator = Operator.NOT_ENDING_IN, likeInLogic = LikeInLogic.AND)
    List<String> name;
}
