package com.example.oauth2_server.dao.redis;

import java.util.Map;

import com.example.oauth2_server.dto.LoginUserAuthenticationDetails;

public interface UserJedisDao {
	
	public void save(LoginUserAuthenticationDetails authDetails);

	public LoginUserAuthenticationDetails findUserByToken(String token);

	public Map<String, LoginUserAuthenticationDetails> findAll();

	public void update(LoginUserAuthenticationDetails authDetails);

	public void delete(String token);
	
	public LoginUserAuthenticationDetails findAuthenticationDetailsByAuthToken();

}
