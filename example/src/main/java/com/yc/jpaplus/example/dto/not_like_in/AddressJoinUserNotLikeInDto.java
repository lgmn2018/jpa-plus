package com.yc.jpaplus.example.dto.not_like_in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.LikeInLogic;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserNotLikeInDto extends JpaPlusDto {
    @Condition(field = "user.name", operator = Operator.NOT_LIKE_IN, likeInLogic = LikeInLogic.AND)
    List<String> name;
}
