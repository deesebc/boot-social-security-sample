package com.bss.sample.app.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.bss.sample.app.bbdd.jpa.User;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 4454136926840298913L;

	List<String> userRoles;

	public CustomUserDetails() {
		super();
		userRoles = new ArrayList<>();
	}

	public CustomUserDetails( final User user, final List<String> userRoles ) {
		try {
			PropertyUtils.copyProperties( this, user );
		} catch( Exception except ) {
			except.printStackTrace();
		}
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString( userRoles );
		return AuthorityUtils.commaSeparatedStringToAuthorityList( roles );
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnable();
	}

}
