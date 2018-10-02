<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<ul>
<%  //_jspService() 메서드 안에 자바코드 작성한다는 것을 잊지말자!
String[] names={"홍길동","임꺽정","유관순"}; 
%>


<% for(String name : names) { %>

<li><% out.write(name); %></li>
<!-- <% out.write("<li>" + name + "</li>"); %> -->

<%} %>
</ul>
</body>
</html>









