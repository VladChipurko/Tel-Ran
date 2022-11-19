package telran.java2022.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests(authorize -> 
			authorize
				.mvcMatchers("/account/register/**", "/forum/posts/**").permitAll()
				.mvcMatchers("/account/user/{login}/role/{role}/**").hasRole("ADMIN")
				.mvcMatchers(HttpMethod.PUT, "/account/user/{login}/**").access("#login == authentication.name")
				.mvcMatchers(HttpMethod.DELETE, "/account/user/{login}/**").access("#login == authentication.name or hasRole('ADMIN')")
				.mvcMatchers(HttpMethod.POST, "/forum/post/{author}/**").access("#author == authentication.name")
				.mvcMatchers(HttpMethod.PUT, "/forum/post/{id}/comment/{author}/**").access("#author == authentication.name")
				.mvcMatchers(HttpMethod.PUT, "/forum/post/{id}").access("@customSecurity.checkPostAuthor(#id, authentication.name)")
				.mvcMatchers(HttpMethod.DELETE, "/forum/post/{id}/**").access("@customSecurity.checkPostAuthor(#id, authentication.name) or hasRole('MODERATOR')")
				.mvcMatchers("/account/user/**", "/account/login/**", "/forum/post/**").access("@customSecurity.checkPasswordExpiration(authentication.name)")
				.anyRequest().authenticated());
		return http.build();
	}
}
