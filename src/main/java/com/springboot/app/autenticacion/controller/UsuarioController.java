package com.springboot.app.autenticacion.controller;

import java.io.Serializable;
import java.util.Date;
import org.springframework.security.core.userdetails.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.autenticacion.config.CustomUser;
import com.springboot.app.personas.modelo.Persona;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UsuarioController implements Serializable{
	
	@PostMapping("/token")
	public User login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = getJWTToken(username);
		CustomUser user = new CustomUser(username, pwd,token, grantedAuthorities);				
		return user;
		
	}

	@GetMapping("/hola")
	public String hola() {
		return "hola";
	}
	
	private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
	    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;		
	}
}
