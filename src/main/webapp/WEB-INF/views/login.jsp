<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="d-flex align-items-center justify-content-center vh-100"
	style="background: linear-gradient(135deg, #7f00ff, #e100ff);">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8 col-lg-6 bg-white p-4 rounded shadow d-flex">
				<!-- Left side (image) -->
				<div
					class="d-none d-md-flex align-items-center justify-content-center flex-column"
					style="flex: 1; padding: 20px;">
					<img src="Image/How-To-Say-Hello-in-10-Languages.jpg"
						alt="Login Illustration" class="img-fluid"
						style="max-width: 200px;">
				</div>

				<!-- Right side (form) -->
				<div class="flex-grow-1 text-center p-3">
					<h2 class="fw-bold">Member Login</h2>
					<form action="login" method="post">
						<div class="mb-3">
							<div class="input-group">
								<span class="input-group-text">👤</span> <input type="text"
									name="username" class="form-control"
									placeholder="Tên Đăng Nhập">
							</div>
						</div>
						<div class="mb-3">
							<div class="input-group">
								<span class="input-group-text">🔒</span> <input type="password"
									name="password" class="form-control" placeholder="Mật Khẩu">
							</div>
						</div>
						<button type="submit" class="btn btn-success w-100">LOGIN</button>
						<p class="mt-3 mb-1">
							<a href="#" class="text-decoration-none">Forgot Username /
								Password?</a>
						</p>
						<p>
							<a href="register" class="text-decoration-none">Create your Account
								→</a>
						</p>
					</form>
					<p style="color:red">${error}</p>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>