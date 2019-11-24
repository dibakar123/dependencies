package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.model.RegistrationBean;
import com.model.User;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
  
	@PersistenceContext
	EntityManager em;
	
  public boolean register(User user) {
    boolean flag=false;
    try {
 	em.persist(user);
	System.out.println("end");
	flag=true;
    }
    catch(Exception e) { System.out.println("Error:"+e);  }
    return flag;
  }

  public User validateUser(User user) {
	User f =null;
	try{
		f=(User)em.createQuery("SELECT f FROM User f WHERE f.username=:uname and f.password=:pwd")
	         .setParameter("uname", user.getUsername())
	         .setParameter("pwd",user.getPassword())
	         .getSingleResult();
	}
	catch(Exception e) {System.out.println(e); }
	em.close();
	return f;
  }

  public List<User> getUsers(){
  @SuppressWarnings("unchecked")
	List<User> users = em.createQuery("SELECT u FROM  User u").getResultList();
  return  users;
  }
  
 
  public User getMe(String uid) {
	  User user = (User)em.createQuery("SELECT u FROM  User u where u.username=:username")
			  .setParameter("username", uid)
			  .getSingleResult();
	  return  user;
  }
	
  public List<String> getStatuslist(){
	  @SuppressWarnings("unchecked")
	List<String> users  = em.createNativeQuery("SELECT distinct astatus FROM  accounts")
			  .getResultList();
	  return  users;
  }
	
  public boolean addProduct(RegistrationBean r) {
	  em.persist(r);
	  return true;
  }
}

