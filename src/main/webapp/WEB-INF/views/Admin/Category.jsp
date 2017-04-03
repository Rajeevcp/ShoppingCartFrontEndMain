<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

</head>
<body>


	<h4 class="red-text text-center">Category Management</h4>

	<div class="container">
	<div class="panel panel-primary">
  <div class="panel-heading">Category Form</div>
  <div class="panel-body">
  <c:choose>
  <c:when test="${empty category.id}">
 
  <c:url var="addAction" value="manage_category_create"></c:url>
 
  </c:when>
  <c:otherwise>
   
    <c:url var="addAction" value="manage_category_update"></c:url>
    
  </c:otherwise>
  </c:choose>
		<form action="${addAction}" method="post" class="form-inline">
			<div class="form-group">
			<div class="col-xs-8">
				<label for="id" class="col-xs-4 control-label">ID:</label>
				<c:choose>
				<c:when test="${empty category.id}">
				<input type="text" class="form-control" name="id" >
					</c:when>
					<c:otherwise>
					<c:if test="${empty admminCreateCategory  }">
					<input type="text" class="form-control" name="id" value="${category.id }" readonly="readonly" disabled="disabled">
					</c:if>
					</c:otherwise>
					</c:choose>
			</div>
			</div>
			<div class="form-group">
			<div class="col-xs-8">
				<label for="name" class="col-xs-4 control-label">Name:</label>
				<c:choose>
				<c:when test="${empty category.id}">
				<input class="form-control" type="text" name="name">
				
				</c:when>
				
				<c:otherwise>
				
				<input class="form-control" type="text" name="name" value="${category.name}">
				</c:otherwise>
				</c:choose>
			</div>
			</div>
			<div class="form-group">
			<div class="col-xs-8">
				<label for="description" class="col-xs-4 control-label">Description:</label>
				<c:choose>
				<c:when test="${empty category.id }">
				<textarea class="form-control" type="text-area" name="description"> </textarea>
				</c:when>
				
				<c:otherwise>
				<textarea class="form-control" type="text-area" name="description"> ${category.description } </textarea>
				
				
				</c:otherwise>
				
				
				</c:choose>
			</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<c:choose>
			<c:when test="${empty category.id }">
			<input  type="submit" value="Publish">
			</c:when>
			<c:otherwise>
			
			<input  type="submit" value="Update">
			</c:otherwise>
			
			</c:choose>
		</form>
		</div>
		</div>
	</div>
	<div class="container">
		<table border="2" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="category" items="${categoryList}">
			<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<th scope="row">${count}</th>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>${category.description}</td>
					<td>
					<a class="btn btn-primary"
									onclick="return confirm('Are you sure you want to edit this category?');"
									href="<c:url value='/manage_category_edit/${category.id}' />"> 
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
								</a>

								<a class="btn btn-primary"
									onclick="return confirm('Are you sure you want to delete this Category?');"
									href="<c:url value='manage_category_delete/${category.id}' />"> 
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
								</a>

							</nobr>
					
					
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


<nobr>
								