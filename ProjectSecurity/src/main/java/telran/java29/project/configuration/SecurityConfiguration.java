package telran.java29.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.POST, "/registration");
		web.ignoring().antMatchers(HttpMethod.POST, "/reservation/confirm");
		web.ignoring().antMatchers(HttpMethod.POST, "/car/{serial_number}/reservation");
		web.ignoring().antMatchers(HttpMethod.GET, "/car/{serial_number}");
		web.ignoring().antMatchers(HttpMethod.GET, "/car/best");
		web.ignoring().antMatchers(HttpMethod.GET, "/comment");
//		web.ignoring().antMatchers(HttpMethod.GET, "/user/{login}");
//		Get filters
//		Search by filters
//		search cars by coordinates
//		search car 
		//
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		 http.authorizeRequests().anyRequest().authenticated();

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/{id}").hasRole("USER");

	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
