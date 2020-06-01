package com.example.oauth2_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	 
	 @Autowired
	    private PasswordEncoder passwordEncoder; 
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		  .withUser("Shivani")
          .password(passwordEncoder.encode("shiva123"))
          .roles("USER","ADMIN","MANAGER").authorities("CAN_READ","CAN_WRITE","CAN_DELETE").and()
          .withUser("Shalu")
          .password(passwordEncoder.encode("shalu"))
          .roles("USER").authorities("CAN_READ","CAN_WRITE");
	}
	
	  @Override
	    public void configure(HttpSecurity http) throws Exception {
	        /*http
	        .cors().and().csrf().disable();
	         http.authorizeRequests()
	        .antMatchers("/oauth/token").permitAll()
	        .anyRequest().authenticated()
	        .and().httpBasic();*/
	        
		  http
          .requestMatchers()
          .antMatchers("/oauth/token", "/login**", "/error**")
          .and().authorizeRequests()
          .anyRequest().authenticated()
          .and().formLogin().permitAll();
	    }
	  
	  @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    } 
	
}
