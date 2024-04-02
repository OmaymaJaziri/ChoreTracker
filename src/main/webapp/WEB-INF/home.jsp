<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chore Tracker Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .mt-3 { margin-top: 1rem; }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1>Welcome <c:out value="${user.firstName} ${user.lastName}" />!</h1>

    <form action="/logout" method="POST">
        <button type="submit" class="btn btn-danger">Logout</button>
    </form>
    
    <a href="/addChore" class="btn btn-primary mt-3">Add A Chore</a>
    
    <div class="row mt-3">
        <div class="col-md-6">
            <h2 class="text-center">Available Jobs</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Location</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="chore" items="${chores}">
                        <c:if test="${chore.employee.id == null}">
                            <tr>
                                <td>${chore.title}</td>
                                <td>${chore.location}</td>
                                <td>
                                    <a href="/view/${chore.id}" class="btn btn-light">View</a>
                                    <form action="/chores/${chore.id}/add" method="POST" style="display: inline;">
                                        <button type="submit" class="btn btn-light">Add</button>
                                    </form>
                                    <c:if test="${user == chore.employer}">
                                        <a href="/edit/${chore.id}" class="btn btn-light">Edit</a>
                                        <form action="/chores/delete/${chore.id}" method="post" style="display: inline;">
                                            <input type="hidden" name="_method" value="delete">
                                            <button type="submit" class="btn btn-danger">Cancel</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div class="col-md-6">
            <h2 class="text-center">My Jobs</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="chore" items="${chores}">
                        <c:if test="${user.id == chore.employee.id}">
                            <tr>
                                <td>${chore.title}</td>
                                <td>
                                    <a href="/view/${chore.id}" class="btn btn-light">View</a>
                                    <form action="/chores/delete/${chore.id}" method="post" style="display: inline;">
                                        <input type="hidden" name="_method" value="delete">
                                        <button type="submit" class="btn btn-success">Done</button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>