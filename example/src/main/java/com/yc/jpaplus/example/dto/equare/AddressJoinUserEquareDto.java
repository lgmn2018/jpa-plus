package com.yc.jpaplus.example.dto.equare;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class AddressJoinUserEquareDto extends JpaPlusDto {
    @Condition(field = "user.name")
    private String name;
}
