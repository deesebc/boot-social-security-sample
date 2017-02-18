package com.bss.sample.app.bbdd.dao;

import com.bss.sample.app.bbdd.jpa.User;

public interface UserDao extends GenericDao<User, Long> {

	User findByUsernameOrEmail( final String username, final String email );

}
