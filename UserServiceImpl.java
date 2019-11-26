package com.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDaoImpl;
import com.model.SpringException;
import com.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  public UserDaoImpl userDao;

  @Transactional(rollbackFor = org.hibernate.exception.ConstraintViolationException.class) //SpringException.class)
  public boolean register(User user) {
    return userDao.register(user);
  }

  @Transactional
  public User validateUser(User user) {
    return userDao.validateUser(user);
  }

  @Transactional
  public List<User> getUsers(){
	  List<User>  list = userDao.getUsers();
	  return list;
  }
  
  @Transactional
  public User getMe(String uid) {
	  return userDao.getMe(uid);
  }
}
