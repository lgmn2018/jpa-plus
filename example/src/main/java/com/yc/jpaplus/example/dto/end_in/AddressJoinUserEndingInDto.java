package com.yc.jpaplus.example.dto.end_in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserEndingInDto extends JpaPlusDto {
    @Condition(field = "user.name", operator = Operator.ENDING_IN, likeInLogic = LikeInLogic.OR)
    List<String> name;
}
