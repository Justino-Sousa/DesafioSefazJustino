package br.com.sefaz.web.model;

import java.io.Serializable;

public class Login implements Serializable{


	private String email;
	private String senha;
	
	public Login() {}
	
	public Login(String email,String senha) {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
	
}
