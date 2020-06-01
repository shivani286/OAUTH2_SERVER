package com.example.oauth2_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@SpringBootApplication
public class ServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(ServletInitializer.class);

	public static void main(String[] args) {
		SpringApplication.run(ServletInitializer.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}

	 @Bean
	 HttpSessionStrategy httpSessionStrategy() {
	    return new HeaderHttpSessionStrategy();
	 }
	 
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}*/
	
	/*@Bean
	  JedisConnectionFactory jedisConnectionFactory(){
	    return new JedisConnectionFactory();
	  }

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	 @Bean
	    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
	        return new HttpSessionSecurityContextRepository();
	    }*/
	
}
