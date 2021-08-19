<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
</head>
<body>
	Update Employee here
	
	<form action="${pageContext.request.contextPath}/EmployeeController?action=UPDATE" method="get">
		<div class="form-row">

			<div class="form-group">
				<label for="lbleid">Employee ID</label> 
				<input type="text"	class="form-control" id="lbleid" name="txteid" value="${param.eid}" readonly="readonly"/>
			</div>

			<div class="form-group">
				<label for="lblename">Employee Name </label> 
				<input type="text"	class="form-control" id="lblename" name="txtename" value="${param.ename }" placeholder="Edit Name" />
			</div>
			<div class="form-group">
				<label for="lbldept">Department </label> 
				<input type="text"	class="form-control" id="lbldept" name="txtdept" value="${param.dept }"/>
			</div>
		</div>
		<button type="submit" name="action" value="UPDATE"  class="btn btn-primary">Update Employee</button>
	</form>
</body>
</html>