	package com.abc.controllers;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
	
	import com.abc.services.FollowService;
	
	@Controller
	@RequestMapping("/follow")
	public class FollowController {
			
		
	    private FollowService followService;
		
	    @Autowired
		public FollowController(FollowService followService) {
			this.followService = followService;
		}
	    @PostMapping("/add")
	    @ResponseBody
	    public String followUser(@RequestParam("followingUserId")int followingUserId, @RequestParam("followedUserId") int followedUserId) {
	        followService.followUser(followingUserId, followedUserId);
	        return "Followed successfully!";
	    }
		
	    @PostMapping("/remove")
	    @ResponseBody
	    public String unfollowUser(@RequestParam("followingUserId")int followingUserId,@RequestParam("followedUserId") int followedUserId) {
	        followService.unfollowUser(followingUserId, followedUserId);
	        return "Unfollowed successfully!";
	    }
	    
	
		
	}
