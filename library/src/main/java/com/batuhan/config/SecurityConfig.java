package com.batuhan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(getUserQuery())
                .authoritiesByUsernameQuery(getAuthoritiesQuery()).passwordEncoder(new BCryptPasswordEncoder());
     }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/library/login").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.GET, "/library/user").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/library/user/create").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"library/user/delete").hasRole("ADMIN")

                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    private String getUserQuery() {
        return "SELECT name , password , active FROM user WHERE name = ?";
    }

    private String getAuthoritiesQuery() {
        return "SELECT name, role FROM user WHERE name = ? ";
    }

}