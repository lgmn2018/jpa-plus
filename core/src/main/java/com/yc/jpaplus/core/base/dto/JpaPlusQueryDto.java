package com.yc.jpaplus.core.base.dto;

import com.yc.jpaplus.core.base.common.JpaPlusOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:16
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class JpaPlusQueryDto<T> extends JpaPlusDto<T> {
    private List<JpaPlusOrder> orders;
}