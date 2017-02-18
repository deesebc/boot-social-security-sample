package com.bss.sample.app.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;

import com.bss.sample.app.bbdd.dao.UserDao;
import com.bss.sample.app.bbdd.jpa.User;

public class CustomConnectionSignUp implements ConnectionSignUp {

	private final UserDao usersDao;

	public CustomConnectionSignUp( final UserDao usersDao ) {
		this.usersDao = usersDao;
	}

	@Override
	public String execute( final Connection<?> connection ) {
		String email = "";
		if( true ) {
			Facebook facebook = (Facebook) connection.getApi();
			String[] fields = { "email", "name", "first_name", "last_name" };
			User userProfile = facebook.fetchObject( "me", User.class, fields );
			// seteamos a nulo para poder insertar AI MYSQL
			userProfile.setId( null );
			// generamos password
			userProfile.setPassword( createEncryptPassword() );
			// indicamos como username el correo
			userProfile.setUsername( userProfile.getEmail() );
			userProfile = usersDao.save( userProfile );
			email = userProfile.getEmail();
			System.out.println( "CustomConnectionSignUp: " + email );
		} else {
			UserProfile profile = connection.fetchUserProfile();
			email = profile.getEmail();
		}
		return email;
	}

	/**
	 * @return Valor de la propiedad usersDao
	 */
	public UserDao getUsersDao() {
		return usersDao;
	}

	private String createEncryptPassword() {
		return new BCryptPasswordEncoder().encode( RandomStringUtils.randomAlphanumeric( 17 ) );
	}

}