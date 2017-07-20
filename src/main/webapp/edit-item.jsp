<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
//TODO: if direct access, send back to index

%>

<html>
<head>
<title>Edit Item</title> </head>
<body>
<h3>Edit Item</h3>

<!-- to display form errors -->
<!-- FIXME -->
<%-- <form:errors path="item.*"/> --%>

<p style="color:red;">${error}</p>
<form method="POST" action="/update-item">
	<input type="hidden" name="id" value="${item.id}" />
	Name: <input type="text" name="name" value="${item.name}" required="required"/> <br/>
	
	Type: 
	<select name="type" required="required">
		<option value="other">other</option>
		<option value="Appliance">Appliance</option>
		<option value="Book">Book</option>
		<option value="Clothing/Wearable">Clothing/Wearable</option>
		<option value="Electronic">Electronic</option>
	</select><br/>
	
	Price: <input type="text" name="price" value="${item.price}" /> <br/>
	
	Date acquired(yyyy/mm/dd): <input type="text" name="dateAcquired" value="${item.dateAcquired}" required="required"/><br/>
	
	<p>
		<input type="submit" name="Submit" value="Submit" />
	</p>
</form>
</body>
</html>