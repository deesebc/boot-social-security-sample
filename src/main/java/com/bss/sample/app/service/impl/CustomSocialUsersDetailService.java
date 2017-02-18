package com.bss.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

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
	private UserDetailsService userDetailsService;

	public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {
		return userDetailsService.loadUserByUsername( username );
	}

	public CustomSocialUsersDetailService( final UserDetailsService userDetailsService ) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public SocialUserDetails loadUserByUserId( final String userId ) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = loadUserByUsername( userId );
		return new SocialUser( userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities() );
	}

}
