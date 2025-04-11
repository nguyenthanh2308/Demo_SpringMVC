package com.abc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.entities.User;
import com.abc.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {


	private UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session,Model model) {
		User user = userService.getUserByUserName(username);
		
		if(user != null && user.getPassWord().equals(password)) {
			session.setAttribute("user", user);
			session.setAttribute("user_id", user.getId());
			return "redirect:/";
		}
		
		model.addAttribute("error","Sai tên đăng nhập hoặc mật khẩu");
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam ("username") String username, @RequestParam("password") String password, Model model) {
		User user = new User(username, password);
		
		if(userService.registerUser(user)) {
			return "redirect:/login";
		}
		
		model.addAttribute("error","Đăng ký thất bại");
		return "redirect:/register";
		
	}
	
	
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
