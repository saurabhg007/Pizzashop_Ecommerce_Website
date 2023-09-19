package com.Spring.Controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Spring.MyUserDetailsService;
import com.Spring.User;
import com.Spring.UserNotFoundException;
import com.Spring.Services.EmailService;
import com.Spring.Services.categoryService;
import com.Spring.Services.orderServices;
import com.Spring.Services.productService;
import com.Spring.productbycategoryServ;

import com.Spring.entity.category;
import com.Spring.entity.orderTransactionDetails;
import com.Spring.entity.product;
import com.Spring.productbycategory;



@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController 
{
	@Autowired
	categoryService catserv;
	
	
	@Autowired
	productService proserv;
	
	
	
	@Autowired
	productbycategoryServ procatserv;
	
	@Autowired
	MyUserDetailsService servuser;
	
	@Autowired
	EmailService emserv;
	
	
	@Autowired
	private orderServices orderservices;

	
	
	/*****************************  Category Details *********************************/
	
	
	@GetMapping("allcat")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<category> getAllCat()
	{
		return catserv.getAllCategory();
	}
	
	
	@GetMapping("catbyid/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	public category getById(@PathVariable int id)
	{
		return catserv.getById(id);
	}
	
	
	@PostMapping("addcat")  // insert the new records
	@CrossOrigin(origins = "http://localhost:4200/")
	public void addCategory(@RequestBody category c)
	{
		catserv.addCategory(c);		
	}
	
	@PutMapping("updatecat/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void updateCategory(@RequestBody category c,@PathVariable int id)
	{
		catserv.updateCatgory(c, id);
	}
	
		
	
	@DeleteMapping("deletecat/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deleteCategory(@PathVariable int id)
	{
		catserv.delById(id);
	}
		
	// Pagination 
	
	
	@GetMapping("allcatbypage")	
	public Page<category> allCatByPage(@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		return catserv.allcatbypage(page, size);
	}
	
	
	/***************************************  Product Details *************************************/
	
	
	

	@PostMapping("addprdt")  // insert the new records
	@CrossOrigin(origins = "http://localhost:4200")
	public void addProduct(@RequestParam("pic")MultipartFile file,
			@RequestParam("prnm")String nm,
			@RequestParam("price")int pr,
			@RequestParam("qty")int qty,
			@RequestParam("descrip")String de,
			@RequestParam("ct_catid")int ct,
			@RequestParam("reorder")int r) throws IOException
	{
		product p = new product();
		category c = catserv.getById(ct);
		
		p.setPrnm(nm);
		p.setPrice(pr);
		p.setQty(qty);
		p.setDescrip(de);
		p.setReorder(r);
		p.setCt(c);
		p.setPic(file.getBytes());
		
		
		proserv.addproduct(p);	
	}
	
	
	@GetMapping("allprdt")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<product> getAllPrdt()
	{
		return proserv.getAllproduct();
	}
	
	
	/************************ store procedure call **********************/
	
	@GetMapping("allprdtcat/{catid}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<productbycategory> getAllPrdtCat(@PathVariable("catid")int catid)
	{
		return procatserv.productbycategory(catid);
	}
	
	
	/***************************  User Authentication *******************************/
	
	
	
		
		@GetMapping("home")
		@CrossOrigin(origins = "http://localhost:4200")
		public String apihome()
		{
			return "<h1> API Home </h1>";
		}
		
		
		@GetMapping("listuser")
		public List<User> getallusers()
		{
			return servuser.allusers();
		}
		
		
		@PostMapping("adduser")
		public String adduser(@RequestBody User u)
		{
			return servuser.adduser(u);	
		}

		
		@GetMapping("find/{id}")
		public User findById(@PathVariable("id") int id)
		{
			return servuser.findbyId(id).get();
		}
		
		
		@PutMapping("updateuser/{id}")
		public String updateuser(@RequestBody User u,@PathVariable("id")int id)
		{
			User u1 = servuser.findbyId(id).get();
		
			u1.setUserName(u.getUserName());
			u1.setUserPass(u.getUserPass());
			
			return servuser.updateuser(u1);
		}
		
		@DeleteMapping("/delete/{id}")
		public String deletebyId(@PathVariable("id") int id)
		{
			return servuser.deletebyId(id);
		}
		
		
	

	
	/********************************  Spring Boot Email Service *******************************************/

	@RequestMapping("/sendmail")
	public ResponseEntity<String> checkEmail()
	{
		emserv.sendEmail("gawalisaurabh007@gmail.com", "Check", "Checking Email");
		return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}

	/*****************************  RazorPay EndPoint ********************/
	
	@GetMapping("/getTransaction/{amount}")
	@CrossOrigin(origins = "*")
	public orderTransactionDetails getTransaction(@PathVariable("amount") 
	double amount)
	{
	return orderservices.orderCreateTransaction(amount);
	}
	
}


