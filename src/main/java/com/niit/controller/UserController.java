package com.niit.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.*;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {
	
	@Autowired
	private User user;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HttpSession session;
	
	
	/*@RequestMapping("/user_home")
	public ModelAndView validation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/Home");
	
		return mv;
	}*/




	@RequestMapping("/user_registration")
	public ModelAndView userRegistration(@RequestParam("user") String id,@RequestParam("name") String name,
			@RequestParam("contactinfo") String contact,@RequestParam("email") String mail,@RequestParam("password") String password){
		
		user.setId(id);
		user.setName(name);
		user.setContact(contact);
		user.setMail(mail);
		user.setPassword(password);
		user.setRole("customer");
		userDAO.save(user);
		ModelAndView mv = new ModelAndView("redirect:/home");
		mv.addObject("msg","Registration Success, Please Login Here");
		return mv;
		
	}
	
	//authentication-failure-forward-url="/loginError"
		@RequestMapping(value = "/loginError", method = RequestMethod.GET)
		public String loginError(Model model) {
			
		
			//log.debug("Starting of the method loginError");
			model.addAttribute("errorMessage", "Invalid Credentials.  Please try again.");
			//log.debug("Ending of the method loginError");
			return "Home";

		}
	//<security:access-denied-handler error-page="/accessDenied" />
		@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
		public String accessDenied(Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String id = auth.getName();
			System.out.println(id);
			//log.debug("Starting of the method accessDenied");
			model.addAttribute("errorMessage", "You are not authorized to access this page");
			//log.debug("Ending of the method accessDenied");
			return "Home";

		}
		
		
		

}
