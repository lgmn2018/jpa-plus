package com.yc.jpaplus.example.dto.in;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

import java.util.List;

@Data
public class AddressJoinUserInDto extends JpaPlusDto {
    @Condition(field = "user.name",operator = Operator.IN)
    private List<String> name;
}
