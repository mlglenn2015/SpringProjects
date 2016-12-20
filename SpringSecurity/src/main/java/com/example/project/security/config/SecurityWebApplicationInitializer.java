package com.example.project.security.config;

/**
 * Spring Web application initializer class.
 *
 * Created by mlglenn on 12/20/2016.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
