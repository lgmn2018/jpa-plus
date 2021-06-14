package com.yc.jpaplus.core.base.utils;

import com.yc.jpaplus.core.base.common.JpaPlusOrder;
import com.yc.jpaplus.core.base.dto.JpaPlusPageDto;
import com.yc.jpaplus.core.base.dto.JpaPlusQueryDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/23 21:18
 */
public class DtoUtil {

    public static Sort getQuerySort(JpaPlusQueryDto lgmnListDto) {
        Sort sort= Sort.unsorted();

        List<JpaPlusOrder> lgmnOrders = lgmnListDto.getOrders();
        if (lgmnOrders != null) {
            List<Sort.Order> orders = generateOrders(lgmnOrders);
            sort= Sort.by(orders);
        }
        return sort;
    }

    protected static List<Sort.Order> generateOrders(List<JpaPlusOrder> lgmnOrders) {
        List<Sort.Order> orders = new ArrayList<>();
        for (JpaPlusOrder lgmnOrder : lgmnOrders) {
            Sort.Order order = new Sort.Order(lgmnOrder.getDirection(), lgmnOrder.getProperty());
            orders.add(order);
        }
        return orders;
    }

    public static PageRequest getPageRequest(JpaPlusPageDto pageDto) {
        List<JpaPlusOrder> lgmnOrders = pageDto.getOrders();
        int pageNumber = pageDto.getPageNumber();
        int pageSize = pageDto.getPageSize();

        if (lgmnOrders != null) {
            List<Sort.Order> orders = generateOrders(lgmnOrders);
            return PageRequest.of(pageNumber, pageSize, Sort.by(orders));
        } else {
            if (pageSize == 0) {
                pageDto.setPageSize(20);
                pageSize = 20;
            }
            return PageRequest.of(pageNumber, pageSize);
        }
    }
}