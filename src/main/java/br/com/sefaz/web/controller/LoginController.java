package br.com.sefaz.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;

import br.com.sefaz.web.dao.UsuarioDao;
import br.com.sefaz.web.model.Login;
import br.com.sefaz.web.model.Usuario;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

	private Login login;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
	}

	public LoginController() {
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String logarNoSstema() throws Exception {
		try {
			Usuario usuarioBanco = usuarioDao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			
			if(usuario.getEmail().equals(usuarioBanco.getEmail())  && usuario.getSenha().equals(usuarioBanco.getSenha())) {
				HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("usuario", usuario);
				return "/app/index?faces-redirect=true";	
			}else { 
				return "/security/login?faces-redirect=true";				
			
			}
		
		}catch(Exception e) {
			System.out.println(e);
			return "/security/login?faces-redirect=true";	
		}
		
			
		
	/*	
		 * if(login.getEmail().equals("admin") && login.getSenha().equals("admin")) {
		 * HttpSession session = (HttpSession)
		 * FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 * session.setAttribute("usuario", login); return
		 * "/app/index?faces-redirect=true"; }else { return
		 * "/security/login?faces-redirect=true"; }
		 */
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/security/login?faces-redirect=true";
	}

}
