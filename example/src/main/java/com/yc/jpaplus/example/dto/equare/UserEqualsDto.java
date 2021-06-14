package com.yc.jpaplus.example.dto.equare;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import lombok.Data;

@Data
public class UserEqualsDto extends JpaPlusDto {
    @Condition
    private String name;
}
