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
@EqualsAndHashCode(callSuper = true)
public class JpaPlusPageDto<T> extends JpaPlusQueryDto<T> {
    private int pageNumber = 0;
    private int pageSize = 20;

    public JpaPlusPageDto(int pageNumber, int pageSize) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    public JpaPlusPageDto(int pageNumber, int pageSize, List<JpaPlusOrder> orders) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
        setOrders(orders);
    }

    public JpaPlusPageDto() {
    }

//    @ApiModelProperty(hidden = true)
//    public LgmnPageDto getLgmnPageRequest() {
//        if (getOrders() != null) {
//            return new LgmnPageDto(getPageNumber(), getPageSize(), getOrders());
//        } else {
//            if (getPageSize() == 0) {
//                setPageSize(20);
//            }
//            return new LgmnPageDto(getPageNumber(), getPageSize());
//        }
//    }
}