<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Job</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1>${chore.title}</h1>
    
    <form action="/logout" method="POST">
        <button type="submit" class="btn btn-danger">Logout</button>
    </form>
    <a href="/home" class="btn btn-primary mt-3">Dashboard</a>

    <p>Description: ${chore.description}</p>
    <p>Location: ${chore.location}</p>
    <p>Added by: ${chore.employer.firstName} ${chore.employer.lastName}</p>
    <p>Posted on: ${chore.createdAt}</p>
    
    <c:if test="${chore.employee.id == null}">
		<form action="/chores/${chore.id}/add" method="POST" style="display: inline;">
			<button type="submit" class="btn btn-success">Add to My Jobs</button>
		</form>	  
  	</c:if>
  
    
</div>

</body>
</html>
