package com.oauth2.example.authorizationserver.config;

import com.oauth2.example.authorizationserver.repository.UserDao;
import com.oauth2.example.authorizationserver.repository.UserPasswordDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.oauth2.example.authorizationserver.model.User;
import com.oauth2.example.authorizationserver.model.UserPassword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserPasswordDao userPasswordDao;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

       User user = userDao.findUserByEmailId(name);
        
        if (Objects.isNull(user)) {
			logger.info("************error in loggin ======>>>>>>>");
			throw new UsernameNotFoundException("User2 Not found ...");
		}
		
		UserPassword userPassword = userPasswordDao.findUserPasswordByUserId(user.getUserId());
		logger.info("**************user password object******************" + userPassword.getPassword());
		if (Objects.isNull(userPassword)) {
			throw new UsernameNotFoundException("password Not found ...");
		}
		
		
		//logger.info("**************user password decode******************");
		//return toUserDetails(user,passwordEncoder.encode(rawPassword));
		
        
       /* Optional<User2> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));*/

       // UserDetails userDetails = new AuthUserDetail(user);
		UserDetails userDetails = toUserDetails(user,userPassword.getPassword());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
    
    private UserDetails toUserDetails(User user, String password) {
    	Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), password,authorities);
	}

    
	private Collection<? extends GrantedAuthority> getAuthorities(User user) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            });

        });
        return grantedAuthorities;
    }
}
