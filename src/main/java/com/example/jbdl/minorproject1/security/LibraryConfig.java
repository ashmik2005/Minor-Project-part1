package com.example.jbdl.minorproject1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LibraryConfig extends WebSecurityConfigurerAdapter {
    // Authorities in LMS
    // STUDENT
    // ADMIN | LIBRARIAN

    @Value("${user.authority.student}")
    private String STUDENT_AUTHORITY;

    @Value("${user.authority.admin}")
    private String ADMIN_AUTHORITY;

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/book/**").hasAnyAuthority(STUDENT_AUTHORITY, ADMIN_AUTHORITY)
                .antMatchers("/book/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/transaction/**").hasAnyAuthority(ADMIN_AUTHORITY, STUDENT_AUTHORITY)
                .antMatchers("/transaction/**").hasAuthority(STUDENT_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers("/studentById/**", "/student/all/**").hasAuthority(ADMIN_AUTHORITY)
                .antMatchers("/student/**").hasAuthority(STUDENT_AUTHORITY)
                .and()
                .formLogin();

    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
