package com.jrcg.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jrcg.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		//Retorna usuário logado no sistema.
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			
		} catch (Exception e) {
			return null;
		}
	}
}
