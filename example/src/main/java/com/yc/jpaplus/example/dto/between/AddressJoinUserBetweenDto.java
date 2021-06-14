package com.yc.jpaplus.example.dto.between;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserBetweenDto extends JpaPlusDto {
    @Condition(field = "user.age", operator = Operator.BETWEEN_AND)
    List<Integer> age;
}
