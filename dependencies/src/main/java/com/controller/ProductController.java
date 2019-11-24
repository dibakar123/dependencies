package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.RegistrationBean;
import com.service.UserService;

@Controller
public class ProductController {
	@Autowired
	public UserService userService;

	@ModelAttribute("astatuslist")
	public List<String>  getAccountStatus() {
		List<String> statuslist = userService.getStatuslist();
		return statuslist;
	}

	@RequestMapping(value = "/viewproduct", method = RequestMethod.GET)
	public ModelAndView viewproductget() {
		return new ModelAndView("viewproduct","registrationBean",new RegistrationBean());
	}
	@RequestMapping(value = "/viewproduct", method = RequestMethod.POST)
	public ModelAndView viewproductpost(HttpServletRequest req) {
		String name = req.getParameter("name");
		int age= Integer.parseInt(req.getParameter("age"));
		String astatus= req.getParameter("astatus");
		String dt=req.getParameter("journeydate");
		Date journeydate= null;
		try {
			journeydate=new SimpleDateFormat("yyyy-mm-dd").parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RegistrationBean registrationBean = new RegistrationBean();
		registrationBean.setAge(age);
		registrationBean.setAstatus(astatus);
		registrationBean.setJourneydate(journeydate);
		registrationBean.setName(name);
		userService.addProduct(registrationBean);
		return new ModelAndView("viewproduct","registrationBean",new RegistrationBean());
	}
}
