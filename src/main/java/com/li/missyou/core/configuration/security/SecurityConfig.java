package com.li.missyou.core.configuration.security;

import com.li.missyou.core.configuration.jwt.JwtTokenGenerator;
import com.li.missyou.core.configuration.jwt.JwtTokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage){
        return new JwtAuthenticationTokenFilter(jwtTokenGenerator,jwtTokenStorage);
    }
    @Bean
    public JwtRefreshTokenFilter jwtRefreshTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage){
        return new JwtRefreshTokenFilter(jwtTokenGenerator,jwtTokenStorage);
    }
    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    static class DefaultCOnfigurerAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
        @Autowired
        private JwtRefreshTokenFilter jwtRefreshTokenFilter;
        @Autowired
        private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
        @Autowired
        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
        }


        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().cors()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests().antMatchers(HttpMethod.POST,"/v1/user/register").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(jwtRefreshTokenFilter,JwtAuthenticationTokenFilter.class);
            http.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthenticationEntryPoint);
        }
    }
}
/*public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage){
        return new JwtAuthenticationTokenFilter(jwtTokenGenerator,jwtTokenStorage);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.apply(wxAuthenticationSecurityConfig).and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/v1/user/register")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/v1/user/refresh")
                .permitAll()
                //.antMatchers("/**")
                //.permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin().
                loginPage("/login");
        //http.headers().cacheControl();
        //http.addFilterAt(wxAppletAuthentiacationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    *//*@Bean
    public WxAppletAuthentiacationFilter wxAppletAuthentiacationFilter() {
        WxAppletAuthentiacationFilter wxAppletAuthentiacationFilter = new WxAppletAuthentiacationFilter();
        return wxAppletAuthentiacationFilter;
    }*//*

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //认证管理器 UserDetails的事情他都管
    }
}*/
