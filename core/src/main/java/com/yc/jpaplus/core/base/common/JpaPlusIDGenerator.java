package com.yc.jpaplus.core.base.common;

import com.yc.jpaplus.core.base.utils.SnowFlakeUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
* @Author: TJM
* @Date: 2020/3/18 15:17
*/
public class JpaPlusIDGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return SnowFlakeUtil.getFlowIdInstance().nextId();
    }
}