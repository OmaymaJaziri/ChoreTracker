<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a Job</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-3">Add a Job</h1> 
    
    <form action="/logout" method="POST">
        <button type="submit" class="btn btn-danger">Logout</button>
    </form>   
    
    <a href="/home" class="btn btn-primary mt-3">Dashboard</a>


    <form:form action="/addChore" method="post" modelAttribute="newChore" class="mt-3">

        <div class="form-group">
            <form:label path="title">Title:</form:label>
            <form:errors path="title" cssClass="text-danger"/>
            <form:input path="title" class="form-control"/>
        </div>
        <div class="form-group">
            <form:label path="description">Description:</form:label>
            <form:errors path="description" cssClass="text-danger"/>
        	<form:textarea path="description" rows="4" class="form-control" />
        </div>
        <div class="form-group">
            <form:label path="location">Location:</form:label>
            <form:errors path="location" cssClass="text-danger"/>
            <form:input path="location" class="form-control"/>
        </div>
        
        
        <form:hidden path="employer" value="${user.id}" />
        
        <input type="submit" class="btn btn-primary" value="Submit"/>
        
    </form:form> 
</div>


</body>
</html>
