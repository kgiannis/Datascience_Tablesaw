package com.rt.Tablesaw.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Generic DAO methods
 * @author ykarav
 *
 * @param <T>
 */
public abstract class GenericDAO<T extends Serializable> {
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> clazz;


	//---------- Constructor ----------//
	public final void setClazz( Class<T> clazzToSet ) {
		this.clazz = clazzToSet;
	}
	
	
	
	//---------- Generic CRUD Methods ----------//
	
	public T findById(Long id){
		return this.em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return this.em.createQuery("from " + clazz.getName() + " c").getResultList();
	}
	
	public void save(T entity){
		this.em.persist(entity);
	}
	
	public void update(T entity){
		this.em.merge(entity);
	}
	
	public void deleteById(Long id){
		T entity = findById(id);
		delete(entity);
	}
	
	public void delete(T entity){
		entity = this.em.merge(entity);
		this.em.remove(entity);
	}
	
	
	
	//---------- EntityManager Getter ----------//
	
	public EntityManager getEm() {
		return em;
	}
}
