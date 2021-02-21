<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// getAttribute로 값을 받아오면 Object 타입으로 받아오기 때문에 형변환이 필요하다. 
	List<String> advice =  (List)request.getAttribute("advice");

	for(String msg: advice){
		out.println("<h2>" + msg + "</h2>");
	}
%>

	

</head>
<body>

</body>
</html>