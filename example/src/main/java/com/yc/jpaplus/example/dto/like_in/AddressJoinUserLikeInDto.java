package com.yc.jpaplus.example.dto.like_in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserLikeInDto extends JpaPlusDto {
    @Condition(field = "user.name", operator = Operator.LIKE_IN, likeInLogic = LikeInLogic.OR)
    List<String> name;
}
