<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<a href="employee-form.jsp">Add Employees</a>


${NOTIFICATION}
	
	<form style="padding: 0% 0% 1% 1%" action="${pageContext.request.contextPath}/EmployeeController?action=SEARCH" method="get">
	  <div class="form-row" >
		  <div class="form-group">
		    <label for="lbleid">Search Employee</label>
		    <input type="text" class="form-control" id="lbleid" name="empsearch" placeholder="Enter ID">
		  </div>
	  </div>
	  <button type="submit" name="action" value="SEARCH" class="btn btn-primary">Search</button> 
	 </form>
	 

<table class="table">
  <thead class="thead-light">
    <tr>      
      <th scope="col">EID</th>
      <th scope="col">ENAME</th>
      <th scope="col">DEPT</th>
    </tr>
  </thead>
  <tbody>
     <c:forEach var="employee" items="${allemp}">
    <tr>
    	<td><c:out value="${employee.eid}"></c:out></td>
      	<td><c:out value="${employee.ename}"></c:out></td>
      	<td><c:out value="${employee.dept}"></c:out></td>
        <td><a href="${pageContext.request.contextPath}/EmployeeController?action=EDIT&eid=${employee.eid}&ename=${employee.ename}&dept=${employee.dept}">Edit</a></td>
      	<td><a href="${pageContext.request.contextPath}/EmployeeController?action=DELETE&eid=${employee.eid}">Delete</a></td>
  
    
    
    
    </tr>
    </c:forEach>
  </tbody>
</table>




</body>
</html>