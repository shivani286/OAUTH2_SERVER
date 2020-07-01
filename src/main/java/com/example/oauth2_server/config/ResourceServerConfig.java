package com.example.oauth2_server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
            http    
              //.anonymous().disable()
                .authorizeRequests()
                .antMatchers("/private/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
	 /*@Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.headers().frameOptions().disable()
	            .and().authorizeRequests()
	            .antMatchers("/","/home","/register","/login").permitAll()
	            .antMatchers("/api/**").authenticated();
	         // .antMatchers("/private/**").authenticated();
	    }*/
}
