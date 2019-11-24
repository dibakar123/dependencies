package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;

@Controller
public class MainController {
 @Autowired
 public UserService userService;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("user", new User());
    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(@ModelAttribute("user") User user) {
    boolean flag=userService.register(user);
    if(flag) {
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new User());
    return mav;
    }
    else {
    	ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        mav.addObject("status","Sorry! Registration in incomplete");
        return mav;	
    }
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin() {
    ModelAndView mav = new ModelAndView("login");
    return mav;
  }

  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(@ModelAttribute User iuser , HttpSession session) {
    ModelAndView mav = null;
    User user = userService.validateUser(iuser);
    if (user != null) {
      mav = new ModelAndView("welcome");
      mav.addObject("firstname", user.getFirstname());
      //session manage
      session.setAttribute("userid", iuser.getUsername());
    } else {
      mav = new ModelAndView("login");
      mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }

//Form handling with Model object
@RequestMapping(value = "/contacts", method = RequestMethod.POST)
public ModelAndView addContact(HttpServletRequest request, HttpServletResponse response) {
	 String name= request.getParameter("name");
	 String info = request.getParameter("info");
	 System.out.println(name+"  "+info);
	 ModelAndView mav = new ModelAndView("viewcontact");
     mav.addObject("name", name.toUpperCase());
     mav.addObject("info", info.toLowerCase());
     return mav;  
 }

@RequestMapping(value = "/logout", method = RequestMethod.GET)
public ModelAndView userlogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	 session.invalidate();
	 ModelAndView mav = new ModelAndView("login");
	 mav.addObject("login", new User());
	 return mav;
 }

@RequestMapping(value = "/viewusers", method = RequestMethod.GET)
public ModelAndView viewflight() {
	List<User>  userlist = userService.getUsers();
	 ModelAndView mav = new ModelAndView("viewusers");
	 mav.addObject("userlist", userlist);
	 return mav;
 }

@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
public ModelAndView viewmyprofile(HttpSession session) {
	 ModelAndView mav = new ModelAndView("myprofile");
	 String uid=(String)session.getAttribute("userid");
	 System.out.println(uid);
	 User user = userService.getMe(uid);
	 mav.addObject("user", user);
	 return mav;
 }

}
