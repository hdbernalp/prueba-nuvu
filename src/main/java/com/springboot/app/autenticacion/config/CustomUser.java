package com.springboot.app.autenticacion.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

public class CustomUser extends User{
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public CustomUser(String username, String password, String token,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, false,false, false, authorities);
		this.token=token;
	}
	private static final long serialVersionUID = 1L;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
