package com.khan.pma.security;

import javax.sql.DataSource;

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
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication()
	    .usersByUsernameQuery("select username, password, enabled " +
	        "from user_accounts where username=?")
	    .authoritiesByUsernameQuery("select username, role " +
	        "from user_accounts where username = ?")
	    .dataSource(dataSource)
	    .passwordEncoder(bCryptEncoder);

	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//	//For temporary use, just want it to work
//		return NoOpPasswordEncoder.getInstance();	
//	}
	//here we can define that what logged in user can do
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	//ADMIN must be on top
		.antMatchers("/projects/new").hasAuthority("ADMIN") //1st periority
		.antMatchers("/projects/save").hasAuthority("ADMIN")
		.antMatchers("/projects/update").hasAuthority("ADMIN")
		.antMatchers("/projects/delete").hasAuthority("ADMIN")
		.antMatchers("/employees/new").hasAuthority("ADMIN")//2nd periority
		.antMatchers("/employees/save").hasAuthority("ADMIN")
		.antMatchers("/employees/update").hasAuthority("ADMIN")
		.antMatchers("/employees/delete").hasAuthority("ADMIN")
		.antMatchers("/register").hasAuthority("ADMIN")
		.antMatchers("/","**").permitAll() //3rd periority(if we put this line on top it would allow everything to work)
		.and()
		.csrf().disable()
		.formLogin();
	//if you want to add custom login page (.formLogin().loginPage("/login-page")
	}
}

