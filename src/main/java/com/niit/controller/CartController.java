package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.MyCart;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.User;
import com.sun.javafx.sg.prism.NGShape.Mode;

@Controller
public class CartController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/cart/add/{id}", method = RequestMethod.GET)
	public String saveCart(@PathVariable("id") String id, Model model) {

		User user = (User) session.getAttribute("user");
		// System.out.println(user.getId());
		String user_id = user.getId();
		Product product = productDAO.getProductById(id);
		String product_name = product.getName();
		Double price = product.getPrice();
       
		Long d = System.currentTimeMillis();
		Date today = new Date(d); 
		myCart.setUser_id(user_id);
		myCart.setProduct_name(product_name);
		myCart.setPrice(price);
		myCart.setQuantity(1);
		myCart.setStatus('N');
		myCart.setDate_added(today);
		cartDAO.save(myCart);
		model.addAttribute("cartMsg", "Item added successfully");
		return "redirect:/myCart";
	}

	@RequestMapping(value = "/myCart")
	public ModelAndView showCart() {

		ModelAndView mv = new ModelAndView("Home");
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		System.out.println(user_id);
		List cart_list = (List) cartDAO.list(user_id);
		int count = cart_list.size();
		session.setAttribute("cartSize",count);
		Double total_amount = cartDAO.getTotalAmount(user_id);
		// mv.addObject("myCart",cartDAO.get(user_id));
		mv.addObject("cart", cart_list);

		mv.addObject("TotalAmount", total_amount);
		mv.addObject("userClickedCart", true);
		return mv;
	}

	@RequestMapping(value = "/cart")
	public String ViewCart(Model model) {

		model.addAttribute("userClickedCart", true);
		return "Home";
	}
	
	@RequestMapping("/delete_cart/{id}")
	public ModelAndView deleteCart(@PathVariable("id") String id){
		
		ModelAndView mv = new ModelAndView("redirect:/myCart");
		myCart = cartDAO.get(id);
		cartDAO.delete(myCart);
		mv.addObject("cartDelete","Item deleted");
		return mv;
		
		
	}
}
