package com.yc.jpaplus.core.base.common;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
* @Author: TJM
* @Date: 2020/3/18 15:17
*/
@Data
public class JpaPlusOrder implements Serializable {
    private Sort.Direction direction;
    private String property;

    public JpaPlusOrder() {}
    public JpaPlusOrder(Sort.Direction direction, String property){
        this.direction = direction;
        this.property = property;
    }
}
