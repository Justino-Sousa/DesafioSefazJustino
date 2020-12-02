package br.com.sefaz.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.web.dao.TelefoneDao;
import br.com.sefaz.web.dao.UsuarioDao;
import br.com.sefaz.web.model.Telefone;
import br.com.sefaz.web.model.Usuario;

@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController {
	
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<Usuario> usuarios;
	
	private Telefone telefone;
	private TelefoneDao telefoneDao;
	private List<Telefone> telefones;
	
	
	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDao();
		usuario = new Usuario();
		telefone = new Telefone();
		telefoneDao = new TelefoneDao();
		telefones =  new ArrayList<Telefone>();
				
		try {
			usuarios = usuarioDao.listarUsuarios();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Telefone getTelefone() {
		return telefone;
	}


	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String inserir() {
		try {	
			telefone.setUsuario(usuario);
			telefones.add(telefone);
			usuario.setTelefones(telefones);
			usuarioDao.inserir(usuario);
			usuarios = usuarioDao.listarUsuarios();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Usuario cadastrado com Sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		return "lista";
	}
	
	public String inserirTelefone() {
		
		telefone.setUsuario(usuario);
		try {
			telefoneDao.inserir(telefone);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "lista";	
	}

	public String atualizar() {
		try {
			telefone.setUsuario(usuario);
			telefones.add(telefone);
			usuario.setTelefones(telefones);
			usuarioDao.atualizar(usuario);
			usuarios = usuarioDao.listarUsuarios();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Lembrete editado com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}
	
	public String atualizarTelefone() {
		try {	
			telefoneDao.atualizar(telefone);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Telefone editado com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "lista";
	}
	
	public void selecionar() {

		try {
			usuario = usuarioDao.selecionar(usuario.getId());
			telefone = telefoneDao.selecionar(usuario.getId());

			if (usuario == null || usuario.getId() == 0) {
				usuario = new Usuario();
				throw new Exception("Usuário não encontrado.");
			}

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void selecionarTelefone() {

		try {
			telefone = telefoneDao.selecionar(telefone.getId());
			
			if (telefone == null || telefone.getId() == 0) {
				telefone = new Telefone();
				throw new Exception("telefone não encontrado.");
			}

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void SelecionarFonePorIdUsuario() {
		try {
			telefones = telefoneDao.ProcuraTelefonePorUsuarioId(usuario.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String remover() {
		try {
			usuarioDao.excluir(usuario.getId());
			usuarios = usuarioDao.listarUsuarios();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Usuário removido com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
		return "lista";
	}

}
