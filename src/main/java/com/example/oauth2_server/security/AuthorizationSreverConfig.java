package com.example.oauth2_server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationSreverConfig extends AuthorizationServerConfigurerAdapter {
	
	/*@Autowired
    private final PasswordEncoder passwordEncoder;*/
	@Value("${user.oauth.clientId}")
	private String ClientID;
	@Value("${user.oauth.clientSecret}")
	private String ClientSecret;
	@Value("${user.oauth.redirectUris}")
	private String RedirectURLs;

/*	public AuthorizationSreverConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }*/
	
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
			
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient(ClientID)
				.secret(passwordEncoder.encode(ClientSecret))
				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
				.authorities("READ_ONLY_CLIENT")
				.scopes("read_profile_info")
				.redirectUris(RedirectURLs)
				.accessTokenValiditySeconds(3600)
				.refreshTokenValiditySeconds(240000);
	}

}
