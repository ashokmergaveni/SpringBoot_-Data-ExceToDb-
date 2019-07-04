<%@ page language="java"%>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<style><%@include file="common/sample.css"%></style>
	<html>
	<head>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
	.bs-example{
		margin: 20px;
	}
</style>
	
	
	<script type="text/javascript">
	function approvePopup() {
	    var strconfirm = confirm("Are you sure you want to approve?");
	    if (strconfirm == true) {
	         approve();
	    }else return false;
	}
	function rejectPopup() {
	    var strconfirm = confirm("Are you sure you want to reject?");
	    if (strconfirm == true) {
	         approve();
	    }else return false;
	}
	
	
 function approve() {
			 // setTimeout(function(){
		       document.getElementById('contents').style.visibility="visible";
		         document.getElementById('load').style.display="block";
			 // },1000);
		  return true;
		}
</script>
	</head>
	<body style="background-color:white;">
	
	<form  action="/update-todo" method ="get" >
	<div id="load" style="display:none; text-align: center;"><h1>Please wait while loading</h1></div>	<div class="container" id="contents" >
		<table class="table table-fit" style="background-color:white;border: 1px solid black ;">
			<caption>Your todos are</caption>
			<thead>
			<tr style="background-color:white;">
			<td style="width:14%;"><input id="submit" name="submit" type="submit" value="Approve" onclick="return approvePopup();" style="width:60%;background-color:MediumSeaGreen;"/>
			</td>
			<td><input id="submit" name="submit" type="submit" value="Reject" onclick="return rejectPopup();" style="width:60%;background-color:Peru ;"/>
			</td>
			</tr>
				<tr>
				<th style="width:12%;">Select</th>
					<th style="width:15%;" >Event name</th>
					<th> venue </th>
					<th>Description</th>
					<th>EventId</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
					<td ><input type="checkbox" style="zoom:1.5;" name="eventId" value="${todo.eventId}" /></td>
						<td>${todo.name}</td>
					<%-- 	<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td> --%>
						 <td>${todo.venue}</td>
						 <td width="30%">${todo.description}</td>
						 <td>${todo.eventId}</td>
						<%-- <td><a type="button" class="btn btn-success"
							href="/update-todo?eventId=${todo.eventId}">Approve</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?eventId=${todo.eventId}">Reject</a></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</form>
	</body>
	</html>
<%@ include file="common/footer.jspf" %>
