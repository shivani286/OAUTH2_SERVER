package com.example.oauth2_server.dao.redis.impl;

import org.springframework.stereotype.Repository;

import com.example.oauth2_server.dao.redis.UserJedisDao;
import com.example.oauth2_server.dto.LoginUserAuthenticationDetails;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Repository
public class UserJedisDaoImpl implements UserJedisDao {

	private static final String KEY = "User";

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, String, LoginUserAuthenticationDetails> hashOperations;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	public UserJedisDaoImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(LoginUserAuthenticationDetails authDetails) {
		hashOperations.put(KEY, authDetails.getUserAuthToken(), authDetails);
	}

	@Override
	public LoginUserAuthenticationDetails findUserByToken(String token) {
		String tokens = httpSession.getId();
		return hashOperations.get(KEY, token);
	}

	@Override
	public LoginUserAuthenticationDetails findAuthenticationDetailsByAuthToken() {
		LoginUserAuthenticationDetails authDetails = new LoginUserAuthenticationDetails();
		String token = httpSession.getId();
		authDetails = findUserByToken(token);
		return authDetails;
	}

	@Override
	public Map<String, LoginUserAuthenticationDetails> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void update(LoginUserAuthenticationDetails authDetails) {
		hashOperations.put(KEY, authDetails.getUserAuthToken(), authDetails);
	}

	@Override
	public void delete(String token) {
		hashOperations.delete(KEY, token);
	}

}
