/**
 * 
 */
package com.example.Learnsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

/**
 * @author rajivranjan
 *
 */
@Configuration
@Order(1)
public class DigestAuthenticationconfig extends WebSecurityConfigurerAdapter{

	// 1 Digest Authentication Entry point
	// 2 Digest Authentication filter
	//3 configurer
	
	private DigestAuthenticationEntryPoint getDigestEntryPoint() {
		DigestAuthenticationEntryPoint entrypoint=new DigestAuthenticationEntryPoint();
		entrypoint.setRealmName("admin-digest-relam");
		entrypoint.setKey("somedigestkey");
		return entrypoint;
	}
	
	@Bean
	public PasswordEncoder getEncodedPassword() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("digestrajiv")
		.password(getEncodedPassword().encode("digestrajiv"))
		.roles("USER")
		.and()
		.withUser("admin")
		.password(getEncodedPassword().encode("admin"))
		.roles("ADMIN");
	}
	
	@Bean
	public UserDetailsService getUserService() throws Exception {
		return super.userDetailsServiceBean();
	}
	
	 
	private DigestAuthenticationFilter getDigestFilter() throws Exception {
		DigestAuthenticationFilter filterdigets=new DigestAuthenticationFilter();
		filterdigets.setUserDetailsService(getUserService());
		filterdigets.setAuthenticationEntryPoint(getDigestEntryPoint());
		return filterdigets;
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/admin/**")
		.addFilter(getDigestFilter()).exceptionHandling()
		.authenticationEntryPoint(getDigestEntryPoint())
		.and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
	}
	
	
	
}
