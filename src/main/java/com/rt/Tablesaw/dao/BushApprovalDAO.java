package com.rt.Tablesaw.dao;

import java.sql.Connection;

import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import com.rt.Tablesaw.model.BushApproval;

@Repository
public class BushApprovalDAO extends GenericDAO<BushApproval> {

	public BushApprovalDAO(){
		setClazz(BushApproval.class);
	}
	
	public Connection getConn(){
		return getEm().unwrap(SessionImpl.class).connection();
	}
	
	public void deleteAll(){
		String jpql = "delete from BushApproval ba";
		Query query = super.getEm().createQuery(jpql);
		query.executeUpdate();
	}

}
