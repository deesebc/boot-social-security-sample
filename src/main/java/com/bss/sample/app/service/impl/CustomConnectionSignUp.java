package com.bss.sample.app.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;

import com.bss.sample.app.bbdd.dao.UserDao;
import com.bss.sample.app.bbdd.jpa.User;

@Component
public class CustomConnectionSignUp implements ConnectionSignUp {
	private final UserDao userDao;

	public CustomConnectionSignUp( final UserDao usersDao ) {
		userDao = usersDao;
	}

	@Override
	public String execute( final Connection<?> connection ) {
		String email = "";
		Facebook facebook = (Facebook) connection.getApi();
		String[] fields = { "email", "name", "first_name", "last_name" };
		User userProfile = facebook.fetchObject( "me", User.class, fields );
		// seteamos a nulo para poder insertar AI MYSQL
		userProfile.setId( null );
		// generamos password
		userProfile.setPassword( createEncryptPassword() );
		// indicamos como username el correo
		userProfile.setUsername( userProfile.getEmail() );
		userProfile = userDao.save( userProfile );
		email = userProfile.getEmail();
		return email;
	}

	private String createEncryptPassword() {
		return new BCryptPasswordEncoder().encode( RandomStringUtils.randomAlphanumeric( 17 ) );
	}
}