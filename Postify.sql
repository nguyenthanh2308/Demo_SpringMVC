-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 10, 2025 lúc 04:52 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `postify`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `follows`
--

CREATE TABLE `follows` (
  `following_user_id` int(11) NOT NULL,
  `followed_user_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `follows`
--

INSERT INTO `follows` (`following_user_id`, `followed_user_id`, `created_at`) VALUES
(1, 6, '2025-04-09 15:29:27'),
(1, 7, '2025-04-09 02:31:03'),
(1, 8, '2025-04-09 02:31:01'),
(1, 9, '2025-04-09 15:29:26'),
(1, 10, '2025-04-09 15:29:26'),
(1, 11, '2025-04-09 15:29:25');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `posts`
--

INSERT INTO `posts` (`id`, `title`, `body`, `user_id`, `status`, `created_at`) VALUES
(1, 'Bài viết 1', 'Nội dung bài viết 1', 1, 'public', '2025-04-08 21:33:42'),
(2, 'Bài viết 2', 'Nội dung bài viết 2', 2, 'public', '2025-04-08 21:33:42'),
(3, 'Bài viết 3', 'Nội dung bài viết 3', 3, 'draft', '2025-04-08 21:33:42'),
(4, 'Bài viết 4', 'Nội dung bài viết 4', 4, 'public', '2025-04-08 21:33:42'),
(5, 'Bài viết 5', 'Nội dung bài viết 5', 5, 'draft', '2025-04-08 21:33:42'),
(6, 'Bài viết 6', 'Nội dung bài viết 6', 6, 'public', '2025-04-08 21:33:42'),
(7, 'Bài viết 7', 'Nội dung bài viết 7', 7, 'private', '2025-04-08 21:33:42'),
(8, 'Bài viết 8', 'Nội dung bài viết 8', 8, 'public', '2025-04-08 21:33:42'),
(9, 'Bài viết 9', 'Nội dung bài viết 9', 9, 'draft', '2025-04-08 21:33:42'),
(10, 'Bài viết 10', 'Nội dung bài viết 10', 10, 'public', '2025-04-08 21:33:42'),
(11, 'siuuuu', 'Siuuuuuu', 1, 'public', '2025-04-08 21:34:09'),
(12, 'siuu', 'siuuuuu', 1, 'public', '2025-04-09 02:30:42');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `created_at`) VALUES
(1, 'user', '123', '2025-04-08 21:31:33'),
(2, 'user1', 'pass1', '2025-04-08 21:33:28'),
(3, 'user2', 'pass2', '2025-04-08 21:33:28'),
(4, 'user3', 'pass3', '2025-04-08 21:33:28'),
(5, 'user4', 'pass4', '2025-04-08 21:33:28'),
(6, 'user5', 'pass5', '2025-04-08 21:33:28'),
(7, 'user6', 'pass6', '2025-04-08 21:33:28'),
(8, 'user7', 'pass7', '2025-04-08 21:33:28'),
(9, 'user8', 'pass8', '2025-04-08 21:33:28'),
(10, 'user9', 'pass9', '2025-04-08 21:33:28'),
(11, 'user10', 'pass10', '2025-04-08 21:33:28');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`following_user_id`,`followed_user_id`),
  ADD KEY `followed_user_id` (`followed_user_id`);

--
-- Chỉ mục cho bảng `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`following_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`followed_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
