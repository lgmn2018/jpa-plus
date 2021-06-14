package com.yc.jpaplus.core.base.dto;

import com.yc.jpaplus.core.base.annoation.Condition;
import com.yc.jpaplus.core.base.annoation.enums.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TJM
 * @Date: 2020/3/18 15:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JpaPlusDeleteByIdDto<ID> extends JpaPlusDto {

    @Condition(operator = Operator.IN)
    List<ID> id;

    public JpaPlusDeleteByIdDto(List<ID> ids) {
        if (ids == null) {
            ids = new ArrayList<>();
        }
        setId(ids);
    }
//
//    private void initEntities(T t,List<String> ids){
//        List<T> ts = new ArrayList<>();
//        for (String id : ids) {
//            T temp = null;
//            try {
//                temp = (T) t.getClass().newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            temp.setId(id);
//            ts.add(temp);
//        }
//        setEntities(ts);
//    }
}