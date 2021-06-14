package com.yc.jpaplus.core.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.jpaplus.core.base.vo.JpaPlusPageVo;
import com.yc.jpaplus.core.base.vo.JpaPlusVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/18 15:15
 */
public class VoUtil<V extends JpaPlusVo> {

    private static VoUtil lgmnVoUtil = null;

    private VoUtil() {
    }

    public static VoUtil getInstance() {
        if (lgmnVoUtil == null) {
            lgmnVoUtil = new VoUtil();
        }
        return lgmnVoUtil;
    }

    public void transEntityToVo(Object sourceEntity, Object target) {
        try {
            BeanUtils.copyProperties(sourceEntity, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<V> transListToVoList(List<?> sourceEntity, Class<V> clazz) {
        List<V> voList = new ArrayList<>();
        for (Object item : sourceEntity) {
            V vo = transEntityToVo(item, clazz);
            transEntityToVo(item, vo);
            voList.add(vo);
        }
        return voList;
    }

    public JpaPlusPageVo<V> transPageToVoPage(Page<?> page, Class<V> clazz) {
        JpaPlusPageVo<V> voPage = new JpaPlusPageVo<>();
        List<V> voList = transListToVoList(page.getContent(), clazz);
        voPage.setList(voList);
        voPage.setPageSize(page.getPageable().getPageSize());
        voPage.setPageNumber(page.getPageable().getPageNumber());
        voPage.setTotalPage(page.getTotalPages());
        voPage.setCount(page.getTotalElements());
        return voPage;
    }

    public V transEntityToVo(Object entity, Class<V> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        V vo = null;
        try {
            vo = objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vo;
    }
}