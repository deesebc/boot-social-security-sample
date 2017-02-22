package com.bss.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bss.sample.app.bbdd.dao.UserDao;
import com.bss.sample.app.bbdd.jpa.User;
import com.bss.sample.app.dto.CustomUserDetails;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
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

}
