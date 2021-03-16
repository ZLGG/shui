package com.gs.lshly.middleware.auth.config;

import com.gs.lshly.common.constants.SecurityConstants;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.auth.rbac.IMenuSet;
import com.gs.lshly.middleware.auth.security.AuthEntryPoint;
import com.gs.lshly.middleware.auth.security.JwtAuthenticationFilter;
import com.gs.lshly.middleware.auth.security.JwtAuthorizationFilter;
import com.gs.lshly.middleware.auth.security.handler.AuthDeniedHandler;
import com.gs.lshly.middleware.auth.security.handler.AuthFailedHandler;
import com.gs.lshly.middleware.auth.security.handler.AuthLogoutHandler;
import com.gs.lshly.middleware.auth.security.handler.AuthSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lxus
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler successHandler;
    @Autowired
    private AuthFailedHandler failedHandler;
    @Autowired
    private AuthDeniedHandler authDeniedHandler;
    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private AuthLogoutHandler authLogoutHandler;

    @Value("${auth.prefixs}")
    private String[] authUriPrefix;

    @Value("${auth.bean}")
    private String authBeanName;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF
        http.cors().and().csrf().disable()

                .authorizeRequests()
                // 指定路径下的资源需要验证了的用户才能访问
                .mvcMatchers(authUriPrefix).authenticated()
                // 其他都放行了
                .anyRequest().permitAll()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .httpBasic().authenticationEntryPoint(authEntryPoint)

                .and()
                .exceptionHandling().accessDeniedHandler(authDeniedHandler).authenticationEntryPoint(authEntryPoint)

                .and()
                .logout().logoutUrl(SecurityConstants.AUTH_LOGOUT_URL)
                .logoutSuccessHandler(authLogoutHandler)
                .permitAll();

        http.addFilter(jwtAuthenticationFilter()).addFilter(jwtAuthorizationFilter());

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = (UserDetailsService) getApplicationContext().getBean(authBeanName);
        auth.userDetailsService(userDetailsService).passwordEncoder(PwdUtil.encoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(successHandler, failedHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManagerBean(), failedHandler, authUriPrefix, (IMenuSet) getApplicationContext().getBean(authBeanName));
    }

}
