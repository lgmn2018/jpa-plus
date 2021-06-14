package com.yc.jpaplus.core.base.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:17
*/
@Data
public class JpaPlusPage<T> implements Serializable {
    List<T> list;
    int totalPage;
    int pageSize;
    int pageNumber;
    Long count;

    public JpaPlusPage(Page<T> page) {
        this.list = page.getContent();
        this.totalPage = page.getTotalPages();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.count = page.getTotalElements();
    }

    public JpaPlusPage(JpaPlusPage lgmnPage) {
        this.totalPage = lgmnPage.getTotalPage();
        this.pageSize = lgmnPage.getPageSize();
        this.pageNumber = lgmnPage.getPageNumber();
        this.count = lgmnPage.getCount();
    }

    public JpaPlusPage() {

    }

    public <V> JpaPlusPage<V> convertVoPage (List<V> voList){
        JpaPlusPage<V> newPage=new JpaPlusPage<V>();
        newPage.setCount(this.getCount());
        newPage.setList(voList);
        newPage.setTotalPage(this.getTotalPage());
        newPage.setPageSize(this.getPageSize());
        newPage.setPageNumber(this.getPageNumber());
        return newPage;
    }
}