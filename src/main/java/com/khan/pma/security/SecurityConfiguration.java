package com.khan.pma.security;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("myuser").password("pass").roles("USER")
		.and()
		.withUser("imran").password("pass2").roles("USER")
		.and()
		.withUser("managerUser").password("pass3").roles("ADMIN");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// For temporary use, just want it to work
		return NoOpPasswordEncoder.getInstance();
	}

	// here we can define that what logged in user can do
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

			.antMatchers("/projects/new").hasRole("ADMIN").antMatchers("/projects/save").hasRole("ADMIN")
				.antMatchers("/employees/new").hasRole("ADMIN").antMatchers("/employees/save").hasRole("ADMIN")

				.antMatchers("/", "/**").permitAll().and().formLogin();

		http.csrf().disable();

	}
}
