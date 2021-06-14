package com.yc.jpaplus.core.repository;

import com.yc.jpaplus.core.base.dto.JpaPlusDeleteByIdDto;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import com.yc.jpaplus.core.base.dto.JpaPlusPageDto;
import com.yc.jpaplus.core.base.dto.JpaPlusQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
* @Author: TJM
* @Date: 2020/3/18 15:16
*/
@NoRepositoryBean
public interface JpaPlusRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    void deleteByIds(JpaPlusDeleteByIdDto dto);

    List<T> findAll(JpaPlusDto queryDto);

    List<T> findAll(JpaPlusDto queryDto, Sort sort);

    List<T> findAll(JpaPlusQueryDto queryDto);

    Page<T> findAll(JpaPlusDto pageDto, Pageable pageable);

    Page<T> findAll(JpaPlusPageDto pageDto);

//    LgmnPage<T> getPageByDto(JpaPlusDto dto, LgmnPageDto lgmnPageRequest) throws Exception;
}