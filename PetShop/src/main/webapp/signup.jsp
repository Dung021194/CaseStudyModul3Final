<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
  <head>
  	<title>Sign Up 09</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="css/style.css">

	</head>
	<body class="img" style="background-image: url(images/bg.jpg);">
	<section class="tco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">Sign Up #09</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap">
		      	<h3 class="text-center mb-4">Create Your Account</h3>
						<form action="signupAccount" method="post" class="signup-form">
		      		<div class="form-group mb-3">
		      			<%--@declare id="name"--%><label class="label" for="name">Full Name</label>
		      			<input type="text" name="fullName" class="form-control" placeholder="Mạnh Tường">
		      			<span class="icon fa fa-user-o"></span>
		      		</div>
							<p class="text-light" size="80">${messSignup}</p>
		      		<div class="form-group mb-3">
		      			<%--@declare id="email"--%><label class="label" for="email">Email Address</label>
		      			<input type="text" name="email" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" class="form-control" placeholder="manhtuong@gmail.com">
		      			<span class="icon fa fa-paper-plane-o"></span>
		      		</div>
	            <div class="form-group mb-3">
	            	<label class="label" for="password">Password</label>
	              <input id="password" name="password" pattern="^.{6,8}$" type="password" class="form-control" placeholder="Password">
	              <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
	              <span class="icon fa fa-lock"></span>
	            </div>
							<p class="text-light">${messSignupRepass}</p>
	            <div class="form-group mb-3">
	            	<label class="label" for="Password">Re-Password</label>
	              <input id="password-confirm" name="rePassword" type="password" class="form-control" placeholder="Password">
	              <span toggle="#password-confirm" class="fa fa-fw fa-eye field-icon toggle-password"></span>
	              <span class="icon fa fa-lock"></span>
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary submit px-3">Sign Up</button>
	            </div>
	          </form>
	          <p>I'm already a member! <a href="login.jsp">Sign In</a></p>
	        </div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>

	</body>
</html>

