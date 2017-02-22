package com.bss.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.bss.sample.app.bbdd.dao.UserDao;
import com.bss.sample.app.bbdd.jpa.User;
import com.bss.sample.app.dto.CustomUserDetails;

/**
 * Clase que permite el Sign in de redes sociales
 *
 * @author dsbc
 * @version 1.0
 * @created: 20 sept. 2016
 */
@Service
public class CustomSocialUsersDetailService implements SocialUserDetailsService {
	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {
		User user = userDao.findByUsernameOrEmail( username, username );
		if( user == null ) {
			throw new UsernameNotFoundException( "No user present with username/email: " + username );
		} else {
			List<String> userRoles = new ArrayList<>();
			userRoles.add( "USER" );
			return new CustomUserDetails( user, userRoles );
		}
	}
	@Override
	public SocialUserDetails loadUserByUserId( final String userId ) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = loadUserByUsername( userId );
		return new SocialUser( userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities() );
	}
}
