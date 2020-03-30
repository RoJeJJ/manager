package com.roje.web.server.configuration.security;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class DefaultPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O o) {

        return null;
    }
}
