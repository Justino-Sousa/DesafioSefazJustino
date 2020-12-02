package br.com.sefaz.web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sefaz.web.model.Telefone;

public class TelefoneDao {

	@SuppressWarnings("unchecked")
	public List<Telefone> listarTelefones() throws Exception {
		
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		List<Telefone> telefones =  null;
		try {
			telefones = em.createQuery("from Telefone").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			em.close();
		}
		
		return telefones;
	}

	public void inserir(Telefone telefone) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(telefone);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally {
			em.close();
		}
	}

	public void atualizar(Telefone telefone) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(telefone);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally {
			em.close();
		}
	}

	public void excluir(Long id) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Telefone telefone = em.find(Telefone.class, id);
			em.remove(telefone);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally {
			em.close();
		}
	}

	public Telefone selecionar(Long id) throws Exception{
		Telefone telefone;
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		
		try {
			telefone = em.find(Telefone.class, id);
		}finally {
			em.close();
		}
		return telefone;
	}
	
	public List<Telefone> ProcuraTelefonePorUsuarioId (Long id) throws Exception{
		List <Telefone> telefones;
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		String query = "SELECT t FROM t Telefone WHERE t.fk_usuario = :idUsuarioVar" ;
		try {
			telefones = em.createQuery(query,Telefone.class).setParameter("idUsuarioVar", id).getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return telefones;
	} 
	

}
