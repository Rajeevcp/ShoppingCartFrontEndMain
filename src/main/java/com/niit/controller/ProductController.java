package com.niit.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.PostPersist;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class ProductController {

	@Autowired
	private Product product;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;
	
	@Autowired
	Category category;

	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping(value="/manage_product_create",method = RequestMethod.POST)
	public String createProductManagement(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("price") Double price,
			@RequestParam("category") String category_id,@RequestParam("supplier") String supplier_id,@RequestParam("description") String description,@RequestParam("Image") MultipartFile img, Model model ) throws InterruptedException{
		System.out.println("product create started");
		//ModelAndView mv = new ModelAndView("redirect:/manage_product_create");
		product.setId(id);
		product.setName(name);
		product.setCategory_id(category_id);
		product.setSupplier_id(supplier_id);
		product.setDescription(description);
		product.setPrice(price);
		
		product.setImage(img);
		System.out.println(img.getName());
		
		Path paths=Paths.get("C:\\Users\\HP-LAP\\workspace\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\productimg\\"+product.getId()+".png");
		try 
		{
		img.transferTo(new File(paths.toString()));
		Thread.sleep(5000);
		//return "redirect:/getAllFlowers";
		} 
		catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println(img.getName());
		productDAO.save(product);
		
		return "redirect:/manage_product";
	}
	
	@RequestMapping(value="/manage_product_edit",method = RequestMethod.GET)
	public String manageProductEdit(Model model) {
		List<Product> productList = productDAO.list();
		//ModelAndView mv = new ModelAndView("/Admin/AdminHome")type name = new type();
		
		model.addAttribute("isUserClickedProduct", true);
		model.addAttribute("isAdminClickedProduct", "true");

		model.addAttribute("productList", productList);
		model.addAttribute("product", product);
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("supplier",supplier);
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("category",category);
		
		return "/Home";

	}
	
	
	@GetMapping("/delete_product/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id)
	{
		
		ModelAndView mv = new ModelAndView("redirect:/manage_product");
		Product products = productDAO.getProductById(id);
		if(productDAO.delete(products))
		{
			mv.addObject("message", "Successfully delete the product");
		}
		else
		{
			mv.addObject("message", "Note able delete the product please contact administrator");
		}
		
		return mv;
		
		
	}
	
	@RequestMapping(value="/manage_product_edit/{id}",method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") String id, Model model){
	
		
		
		 product = productDAO.getProductById(id);
			//List<Category> categoryList = categoryDAO.list();
		

		return "redirect:/manage_product_edit";
		
	}
	@RequestMapping(value="/manage_product_update")
	public String updateCategory(@RequestParam("name") String name,@RequestParam("price") Double price,
			@RequestParam("category") String category_id,@RequestParam("supplier") String supplier_id,@RequestParam("description") String description, Model model){
		//category.setId(id);
		product.setName(name);
		product.setCategory_id(category_id);
		product.setSupplier_id(supplier_id);
		product.setPrice(price);
		product.setDescription(description);
		productDAO.update(product);
		
		return "redirect:/manage_product";
	}
	@RequestMapping(value="/display_product/get/{id}",method=RequestMethod.GET)
	public String showProductById(@PathVariable("id") String id, Model model){
		
		
       
		product = productDAO.getProductById(id);
		model.addAttribute("product",product);
		return "redirect:/show_product";
		
	}
	@RequestMapping(value="/show_product",method=RequestMethod.GET)
	public String showProduct(Model model) {
		 model.addAttribute("userClikedProduct",true);
		model.addAttribute("product",product);
		return "/Home";
	}
}
