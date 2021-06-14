package com.cy.jpaplus.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lonel
 */
@Configuration
@ConditionalOnClass(JpaPlusService.class)
@EnableConfigurationProperties(JpaPlusProperties.class)
@Slf4j
public class JpaPlusConfigure {

}
