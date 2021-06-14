package com.yc.jpaplus.core.repository.annotation;

import com.yc.jpaplus.core.repository.impl.JpaPlusRepositoryImpl;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

/**
 * @Author: TJM
 * @Date: 2020/3/21 14:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableJpaRepositories(repositoryBaseClass = JpaPlusRepositoryImpl.class)
@EnableJpaAuditing
public @interface EnableJpaPlusRepository {
}
