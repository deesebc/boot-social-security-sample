package com.bss.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	private final static String LOGIN_PAGE = "/login";
	private final static String LOGOUT_PAGE = "/logout";

	private final static String[] PUBLIC_PAGES = { "/", "/error", LOGIN_PAGE, LOGOUT_PAGE };
	private final static String[] RESTRICTED_PAGES = { "/restricted" };

	@Override
	public void configure( final WebSecurity web ) throws Exception {
		web.ignoring().antMatchers( "/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.js" );
	}

	@Override
	protected void configure( final HttpSecurity http ) throws Exception {
		// @formatter:off
		http.authorizeRequests()
				// configure free access urls
				.antMatchers( PUBLIC_PAGES ).permitAll()
				// configure user pages
				.antMatchers( RESTRICTED_PAGES ).fullyAuthenticated()
				// configure web security
				.anyRequest().authenticated()
				// configures form login
				.and().formLogin().loginPage( LOGIN_PAGE ).failureUrl( "/login?error=bad_credentials" )
				// configure form logout
				.and().logout().logoutUrl( LOGOUT_PAGE ).deleteCookies( "JSESSIONID" ).logoutSuccessUrl( "/" ).permitAll()
				// configure remember me
				.and().rememberMe()
				// Adds the SocialAuthenticationFilter to Spring Security's filter chain.
				.and().apply( new SpringSocialConfigurer().postLoginUrl( "/" ).alwaysUsePostLoginUrl( true ) );
		// @formatter:on
	}

	@Bean( name = "passwordEncoder" )
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configAuthentication( final AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( userDetailsService ).passwordEncoder( passwordencoder() );
	}

}
