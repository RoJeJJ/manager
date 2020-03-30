package com.roje.web.server.configuration.security;

import com.roje.web.server.configuration.WebProperties;
import com.roje.web.server.service.SysUserService;
import com.roje.web.server.utils.HttpUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Ro
 */
@Configuration
public class RojeWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final SysUserService userService;


    private final WebProperties webProperties;

    public RojeWebSecurityConfigurer(SysUserService userService, WebProperties webProperties) {
        this.userService = userService;
        this.webProperties = webProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFailureHandler failureHandler = new DefaultAuthenticationFailureHandler();
        http.csrf().disable()
                //设置未授权处理
                .authorizeRequests()
                .requestMatchers(HttpUtils.requestMethodMatcher(HttpMethod.OPTIONS)).permitAll()
//                .antMatchers("/captcha").permitAll()
//                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new DefaultAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new CaptchaFilter(webProperties, failureHandler), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage(webProperties.getLoginProcessUrl()).permitAll()
//                .loginProcessingUrl(webProperties.getLoginProcessUrl()).permitAll()
                .successHandler(new DefaultAuthenticationSuccessHandler())
                .failureHandler(failureHandler)
//                .defaultSuccessUrl("/")
                .and()
                .sessionManagement()
//                .invalidSessionUrl(webProperties.getLoginPage() + "?code=" + ResultCode.SESSION_TIMEOUT.code)
                .invalidSessionStrategy(new DefaultInvalidSessionStrategy())
                .maximumSessions(1)
                .expiredSessionStrategy(new DefaultExpiredSessionStrategy())
                .and().and()
                .logout().logoutSuccessHandler(new DefaultLogoutSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/js/**", "/bootstrap/**", "/img/**", "/css/**", "/fontawesome/**", "/captcha*");
    }
}
