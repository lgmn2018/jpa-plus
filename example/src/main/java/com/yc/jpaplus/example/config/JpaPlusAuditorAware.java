package com.yc.jpaplus.example.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class JpaPlusAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Long userId = (Long) servletRequestAttributes.getRequest().getSession().getAttribute("userId");
        return Optional.ofNullable(userId);
    }
}