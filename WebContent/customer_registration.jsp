<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Customer Register here...</h1>
<form action="regCustomer" method="post">
<label for="tbName">Name:</label>
<input type="text" name="tbName" id="tbNmae"/>
<br>
<label for="tbEmail">Email:</label>
<input type="email" name="tbEmail" id="tbEmail"/>
<br>
<label for="tbMobile">Mobile:</label>
<input type="tel" name="tbMobile" id="tbMobile"/>
<br>
<label for="tbPass">Password:</label>
<input type="password" name="tbPass" id="tbPass"/>
<br>
<label for="tbState">State:</label>
<select name="ddlState" id="tbState">
	<option value="">--Select--</option>
	<option value="Tamil Nadu">TN</option>
	<option value="Karnataka">KA</option>
	<option value="Kerela">KL</option>
 </select>
<br>
<input type="submit" value="Register"/>
</form>	
	
</body>
</html>