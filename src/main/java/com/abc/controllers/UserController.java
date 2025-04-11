package com.abc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.entities.Post;
import com.abc.entities.User;
import com.abc.services.FollowService;
import com.abc.services.PostService;
import com.abc.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    private PostService postService;
    private UserService userService;
    private FollowService followService;

    @Autowired
    public UserController(PostService postService, UserService userService, FollowService followService) {
        this.postService = postService;
        this.userService = userService;
        this.followService = followService;
    }

    @GetMapping("/profile")
    public String profileUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        
        if (user == null)
            return "redirect:/login";
        
        List<Post> posts = new ArrayList<Post>();
        posts = postService.getPostById(user.getId());
        
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        
        return "profile";
    }

    @GetMapping("/search")
    public String searchUsers(
            @RequestParam(value = "minFollowing", defaultValue = "3") int minFollowing,
            @RequestParam(value = "minFollowers", defaultValue = "5") int minFollowers,
            Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        // Gọi UserService để tìm kiếm người dùng
        List<User> searchResults = userService.searchUsersByFollowCount(minFollowing, minFollowers);
        
        // Tạo danh sách để lưu thông tin user kèm số following và follower
        List<UserFollowStats> userStats = new ArrayList<>();
        
        if (searchResults.isEmpty()) {
            model.addAttribute("notFound", true); // Đánh dấu để hiển thị ảnh not-found
        } else {
            // Lấy số following và follower cho từng user
            for (User u : searchResults) {
                int followingCount = followService.countFollowing(u.getId());
                int followerCount = followService.countFollowers(u.getId());
                userStats.add(new UserFollowStats(u, followingCount, followerCount));
            }
            model.addAttribute("searchResults", userStats); // Truyền kết quả tìm kiếm
        }

        // Lấy lại các dữ liệu cần thiết cho trang home (giống HomeController)
        List<Post> posts = postService.getAllPost(user.getId()); // Sửa thành getAllPost để giống HomeController
        List<User> userfed = followService.getUserFollowed(user.getId()); // Thêm userfed
        List<User> suggestfollow = followService.getSuggestFollow(user.getId()); // Thêm suggestfollow
        
        model.addAttribute("posts", posts);
        model.addAttribute("userfed", userfed);
        model.addAttribute("suggestfollow", suggestfollow);
        
        return "home"; // Trả về home.jsp để hiển thị kết quả
    }

    // Lớp nội tại để lưu thông tin user kèm số following và follower
    public static class UserFollowStats {
        private User user;
        private int followingCount;
        private int followerCount;

        public UserFollowStats(User user, int followingCount, int followerCount) {
            this.user = user;
            this.followingCount = followingCount;
            this.followerCount = followerCount;
        }

        public User getUser() {
            return user;
        }

        public int getFollowingCount() {
            return followingCount;
        }

        public int getFollowerCount() {
            return followerCount;
        }
    }
}