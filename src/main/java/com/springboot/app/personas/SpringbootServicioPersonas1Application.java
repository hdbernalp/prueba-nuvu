package com.springboot.app.personas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.springboot.app.autenticacion.config.JWTAuthorizationFilter;
import com.springboot.app.autenticacion.controller.UsuarioController;
import com.springboot.app.personas.controller.PersonaController;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebMvc
@ComponentScan(basePackageClasses = UsuarioController.class)
@ComponentScan(basePackageClasses = PersonaController.class)
public class SpringbootServicioPersonas1Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootServicioPersonas1Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioPersonas1Application.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().disable();
			http.cors().and().csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/user/**").permitAll()
				.anyRequest().authenticated();
		}
	}
}
