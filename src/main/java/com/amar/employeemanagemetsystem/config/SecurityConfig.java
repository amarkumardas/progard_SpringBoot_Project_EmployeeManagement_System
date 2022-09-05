package com.amar.employeemanagemetsystem.config;

import com.amar.employeemanagemetsystem.security.EmployeeDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeDetailsImpl employeeDetailsImpl;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()//for session management ie to delete cookies
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//SessionCreationPolicy. STATELESS – Spring Security will never create a HttpSession and it will never use it to get the SecurityContext
                .and()//add() is acting like a bridge we have to apply here to connect

                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/emp/").permitAll()
                //employee view
                .antMatchers("/api/emp/getbyid/*","/api/asset/getall").hasAnyAuthority("employee","manager","admin")//***if there is two same api then first api will be access first by role

                //view
                .antMatchers("/api/emp/getall","/api/asset/getbyid/*","/api/org/getbyid/*","/api/org/getall").hasAnyAuthority("manager","admin")
                //update
                .antMatchers("/api/emp/updatebyid/*","/api/asset/updatebyid/*","/api/org/updatebyid/*").hasAnyAuthority("manager","admin")
                //post
                .antMatchers( "/api/asset/","/api/org/").hasAuthority("admin")
                //delete
                .antMatchers( "/api/asset/deletebyid/*","/api/org/deletebyid/*","/api/emp/deletebyid/*").hasAuthority("admin")
                
                .anyRequest()
                .authenticated()
                .and()//bridge to connect httpBasic()
                .httpBasic();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.employeeDetailsImpl).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
