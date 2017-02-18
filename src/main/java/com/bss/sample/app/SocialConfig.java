package com.bss.sample.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.bss.sample.app.bbdd.dao.UserDao;
import com.bss.sample.app.service.impl.CustomConnectionSignUp;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDao dao;

	@Override
	public void addConnectionFactories( final ConnectionFactoryConfigurer connectionFactoryConfigurer, final Environment environment ) {
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository( final ConnectionFactoryLocator connectionFactoryLocator ) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository( dataSource, connectionFactoryLocator, Encryptors.noOpText() );
		repository.setConnectionSignUp( new CustomConnectionSignUp( dao ) );
		return repository;
	}
}
