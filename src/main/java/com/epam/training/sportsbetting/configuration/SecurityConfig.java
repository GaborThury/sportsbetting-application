/*
package com.epam.training.sportsbetting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}pass")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}pw")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/").permitAll()
                    .antMatchers("/bets").hasRole("USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                    .formLogin().permitAll();
    }
}
*/
