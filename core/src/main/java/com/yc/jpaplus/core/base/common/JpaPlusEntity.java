package com.yc.jpaplus.core.base.common;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: TJM
 * @Date: 2020/3/18 15:17
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class JpaPlusEntity implements Serializable {
    private static final long serialVersionUID = -2795636865595260832L;

    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "com.yc.jpaplus.core.base.common.JpaPlusIDGenerator")
    private Long id;

    @CreatedBy
    @Column(updatable = false)
    private Long createBy;

    @CreatedDate
    @Column(updatable = false)
    private Date createTime;

    @LastModifiedBy
    private Long updateBy;

    @LastModifiedDate
    private Date updateTime;

    private Integer delFlag = 0;

    @Version
    private Integer version;
}