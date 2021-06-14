package com.yc.jpaplus.core.repository.impl;

import com.yc.jpaplus.core.base.annoation.UpdateNullValue;
import com.yc.jpaplus.core.base.dto.JpaPlusDeleteByIdDto;
import com.yc.jpaplus.core.base.dto.JpaPlusDto;
import com.yc.jpaplus.core.base.dto.JpaPlusPageDto;
import com.yc.jpaplus.core.base.dto.JpaPlusQueryDto;
import com.yc.jpaplus.core.base.utils.DtoUtil;
import com.yc.jpaplus.core.repository.specification.SpecificationConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
* @Author: TJM
* @Date: 2020/3/18 15:16
*/
@Repository
@Transactional(readOnly = true)
public class JpaPlusRepositoryImpl<T, ID>  extends SimpleJpaRepository<T, ID> implements com.yc.jpaplus.core.repository.JpaPlusRepository<T, ID> {
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager entityManager;

    public JpaPlusRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public <S extends T> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            ID id = (ID)entityInformation.getId(entity);
            Optional<T> optional = findById(id);
            if(optional.isPresent()) {
            // 检查dto类是否存在 UpdateNullValue 注解，如果存在则更新实体所有属性值，否则跳过空值属性只更新有不为空的属性
            Boolean updateNullValue = AnnotationUtils.isAnnotationDeclaredLocally(UpdateNullValue.class,entity.getClass());
            if(!updateNullValue) {
                    S oldEntity = (S) findById(id).get();
                    String[] nullProps = getNullProperties(entity);
                    BeanUtils.copyProperties(entity, oldEntity, nullProps);
                    entity = oldEntity;
                }
                entity = entityManager.merge(entity);
                return entity;
            }else{
                throw new NoSuchElementException("can not found record in database of id: " + id);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteByIds(JpaPlusDeleteByIdDto dto) {
//        Specification specification = SpecificationFactory.getSpecification(dto);
        Specification specification = SpecificationConstructor.getSpecification(dto);
        List<T> list = this.findAll(specification);
        this.deleteInBatch(list);
    }

    @Override
    public List<T> findAll(JpaPlusDto queryDto) {
        Specification specification = SpecificationConstructor.getSpecification(queryDto);
        return this.findAll(specification);
    }

    @Override
    public List<T> findAll(JpaPlusDto queryDto, Sort sort) {
        Specification specification = SpecificationConstructor.getSpecification(queryDto);
        return this.findAll(specification, sort);
    }

    @Override
    public List<T> findAll(JpaPlusQueryDto queryDto) {
        Sort sort = DtoUtil.getQuerySort(queryDto);
        Specification specification = SpecificationConstructor.getSpecification(queryDto);
        return this.findAll(specification,sort);
    }

    @Override
    public Page<T> findAll(JpaPlusDto pageDto, Pageable pageable) {
        Specification specification = SpecificationConstructor.getSpecification(pageDto);
        Page returnPage = this.findAll(specification, pageable);
        return returnPage;
    }

    @Override
    public Page<T> findAll(JpaPlusPageDto pageDto) {
        PageRequest pr = DtoUtil.getPageRequest(pageDto);
        Specification specification = SpecificationConstructor.getSpecification(pageDto);
        Page returnPage = this.findAll(specification, pr);
        return returnPage;
    }

//    @Override
//    public LgmnPage<T> getPageByDto(JpaPlusDto dto, LgmnPageDto pageDto) throws Exception {
//        PageRequest pr = pageDto.getPageRequest();
//        Specification specification = SpecificationFactory.getSpecification(dto);
//        LgmnPage returnPage = new LgmnPage(this.findAll(specification, pr));
//        return returnPage;
//    }


    /**
     * 将为空的properties给找出来,然后返回出来
     *
     * @param src
     * @return
     */
    private String[] getNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> emptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());
            if (srcValue == null) {
                emptyName.add(p.getName());
            }
        }
        String[] result = new String[emptyName.size()];
        return emptyName.toArray(result);
    }
}