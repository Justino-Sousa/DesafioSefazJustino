package br.com.sefaz.web.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaResourceBean {
	
	public static EntityManagerFactory entityManagerFactory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("desafioSefaz");
		}
		
		return entityManagerFactory;
	}
	
}
