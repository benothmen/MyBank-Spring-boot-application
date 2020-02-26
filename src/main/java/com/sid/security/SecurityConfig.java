package com.sid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bcpe=getBCPE();
        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("admin")).roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("user").password(bcpe.encode("12345")).roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/operations","/consulterCompte").hasRole("USER");
        http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}
