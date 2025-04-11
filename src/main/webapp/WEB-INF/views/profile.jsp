<%@page import="java.util.List"%>
<%@ page import="com.abc.entities.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Cá Nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">🏠 Trang chủ</a>
            <div class="ms-auto">
                <button class="btn btn-outline-light">Đăng xuất</button>
            </div>
        </div>
    </nav>

    <div class="container mt-2">
        <div class="row justify-content-center">
            <div class="col-md-3">
                <div class="card p-3 mb-3">
                    <p class="fw-bold">Danh sách người theo dõi</p>
                    <ul class="list-unstyled">
                        <li><img src="Image/7cdb3e40d6dedfc8dcde7c2ddf0abcf6.jpg" alt="Avatar" class="rounded-circle me-2" width="30"> @username1</li>
                        <li><img src="Image/7cdb3e40d6dedfc8dcde7c2ddf0abcf6.jpg" alt="Avatar" class="rounded-circle me-2" width="30"> @username2</li>
                        <li><img src="Image/7cdb3e40d6dedfc8dcde7c2ddf0abcf6.jpg" alt="Avatar" class="rounded-circle me-2" width="30"> @username3</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 mt-0">
                <div class="card mb-3 text-center">
                <% User user = (User) request.getAttribute("user"); %>
                    <div class="card-body">
                        <img src="${pageContext.request.contextPath}/resources/images/avt.jpg" alt="Avatar" class="rounded-circle mb-3" width="150" height="150">
                        <h4 class="card-title"><%= user.getUsername() %></h4>
                        <p><strong>Đang theo dõi:</strong> | <strong>Người theo dõi:</strong></p>
                        <button class="btn btn-primary mb-3">Chỉnh sửa hồ sơ</button>
                        <form class="d-flex gap-2 align-items-center">
                            <select class="form-select w-25">
                                <option selected>Trạng thái</option>
                                <option value="1">Công khai</option>
                                <option value="2">Người theo dõi</option>
                                <option value="3">Chỉ mình tôi</option>
                            </select>
                            <input type="text" class="form-control" placeholder="Bạn đang nghĩ gì?">
                            <button class="btn btn-danger">Đăng</button>
                        </form>
                    </div>
                </div>
                
                <% 
	                List<Post> posts = (List<Post>) request.getAttribute("posts");

                	for(Post post : posts) {
                %>
                <div class="card p-3 mb-3">
                    <div class="d-flex align-items-center justify-content-between mb-2">
                        <div class="d-flex align-items-center">
                            <img src="${pageContext.request.contextPath}/resources/images/avt.jpg" alt="Avatar" class="rounded-circle me-2" width="30">
                            <b><%= user.getUsername() %></b>
                            <span class="text-muted ms-3"><%= post.getCreateAt() %></span>
                        </div>
                        <div class="dropdown">
                            <button class="btn btn-light btn-sm" type="button" data-bs-toggle="dropdown" aria-expanded="false">⋮</button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="#">Chỉnh sửa trạng thái</a></li>
                                <li><a class="dropdown-item" href="#">Xóa bài</a></li>
                            </ul>
                        </div>
                    </div>
                    <p><strong>Trạng thái:</strong> <%= post.getStatus() %></p>
                    <p><%= post.getTitle() %></p>
                    <p><%= post.getBody() %></p>
                </div>
                <% } %>
                
                
                
                
            </div>
            <div class="col-md-3">
                <div class="card p-3 mb-3">
                    <p class="fw-bold">Gợi ý theo dõi</p>
                    <p><img src="Image/7cdb3e40d6dedfc8dcde7c2ddf0abcf6.jpg" alt="Avatar" class="rounded-circle me-2" width="30"> @username <button class="btn btn-sm btn-primary">Theo dõi</button></p>
                </div>
            </div>
        </div>
    </div>

    <footer class="bg-primary text-white text-center py-2 mt-4">
        <div class="container">
            <p>Công ty TNHH Mạng Xã Hội Việt © 2025</p>
            <p>Ngày phát hành: 15/02/2025</p>
            <p>Bản quyền © 2025. Mọi quyền được bảo lưu.</p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
