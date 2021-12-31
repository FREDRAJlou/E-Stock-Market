package com.stockMarket.models;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	String role="";
	
	
	
	public Authority(String role) {
		super();
		this.role = role;
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

	
}
