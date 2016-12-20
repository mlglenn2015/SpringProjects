package com.example.project.security.config;

/**
 * Spring Web Security configuration.
 *
 * Created by mlglenn on 12/20/2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}
