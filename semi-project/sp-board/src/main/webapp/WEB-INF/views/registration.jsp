<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<div align="center">
		<form:form action="register" method="post"	commandName="userForm">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>User Sign Up Page</h2></td>
				</tr>		
				<tr>
					<td>User Name:</td>
					<td><form:input path="name"/></td>
				</tr>
				<tr>
					<td>User NickName:</td>
					<td><form:input path="nickName"/></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><form:input path="email"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password"/></td>
				</tr>
				<tr>
					<td>Phone Number:</td>
					<td><form:input path="phoneNumber"/></td>
				</tr>
				
				<tr>
					<td>Address:</td>
					<td><form:input path="address"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Register"/></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>