package com.abc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.entities.Post;
import com.abc.entities.User;
import com.abc.services.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	@PostMapping("/post")
	public String createPost(@RequestParam("title") String title, @RequestParam("body") String body,HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Post post = new Post(title,body,user.getId(),"public");
		
		
		if(title.isEmpty() || body.isEmpty()) {
			model.addAttribute("error","không được để rỗng title và body");
			return "redirect:/";
		}
		if(postService.createdPost(post)) {
			return "redirect:/";
		}
		
		model.addAttribute("error","Đăng bài viết thất bại");
		
		return "redirect:/";
	}
}
