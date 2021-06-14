package com.yc.jpaplus.core.base.vo;

import lombok.Data;

import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:16
*/
@Data
public class JpaPlusPageVo<V extends JpaPlusVo> {
    List<V> list;
    int totalPage;
    int pageSize;
    int pageNumber;
    Long count;
}