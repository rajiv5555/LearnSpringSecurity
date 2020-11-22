/**
 * 
 */
package com.example.Learnsecurity.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author rajivranjan
 *
 */
@Configuration
@Order(2)
public class Basicauth extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
	.httpBasic();*/
		http.authorizeRequests()
		.antMatchers("/mylogin","/h2-console").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/mylogin").defaultSuccessUrl("/test",true)
		.and()
		.rememberMe().key("uniqueAndSecret").and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/mylogin").deleteCookies("remember-me")
		.and()
		.csrf().disable();
	}
	
	
	 @Autowired
		private DataSource dataSource;
		
		
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication()
			.dataSource(dataSource).passwordEncoder(passwordEncoder);
		}
}
