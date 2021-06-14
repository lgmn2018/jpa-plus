package com.cy.jpaplus.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jpa-plus")
public class JpaPlusProperties {

}
